import { Injectable } from '@angular/core';
import {catchError, Observable, of} from "rxjs";
import {Team} from "./team";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {MessageService} from "./message.service";
import {Router} from "@angular/router";
import {Transfer} from "./transfer";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private teamsUrl = 'http://localhost:8080/teams';  // URL to web api

  constructor(
    private router: Router,
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamsUrl)
      .pipe(
        catchError(this.handleError<Team[]>('getTeam', []))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`PlayerService: ${message}`);
  }

  getTeam(id: number): Observable<Team> {
    const url = `${this.teamsUrl}/${id}`;
    return this.http.get<Team>(url).pipe(
      catchError(this.handleError<Team>(`getTeam id=${id}`))
    );
  }

  updateTeam(team: Team): Observable<any> {
    const url = `${this.teamsUrl}/${team.id}`;
    return this.http.put(url, team, this.httpOptions).pipe(
      catchError(this.handleError<any>('updateTeam')),
    );
  }

  deleteTeam(team: Team | number) {
    const id = typeof team === 'number' ? team : team.id;
    const url = `${this.teamsUrl}/${id}`;

    return this.http.delete<Team>(url, this.httpOptions).pipe(
      catchError(this.handleError<Team>('deleteTeam'))
    ).subscribe(_ => this.router.navigate(['/teams']));
  }

  addTeam(team: Team): Observable<Team> {
    return this.http.post<Team>(this.teamsUrl, team, this.httpOptions).pipe(
      catchError(this.handleError<Team>('addTeam'))
    );
  }

  buyPlayer(toId: number, transfer: Transfer): Observable<Team> {
    const url = `${this.teamsUrl}/${toId}/players`
    return this.http.post<Team>(url, transfer, this.httpOptions).pipe(
      catchError(this.handleError<Team>('buyPlayer'))
    );
  }
}
