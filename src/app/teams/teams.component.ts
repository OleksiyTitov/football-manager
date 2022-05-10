import { Component, OnInit } from '@angular/core';
import {MessageService} from "../message.service";
import {TeamService} from "../team.service";
import {Team} from "../team";
import {PlayerService} from "../player.service";
import {Player} from "../player"
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css']
})
export class TeamsComponent implements OnInit {
  showFormCreate: boolean = false;
  teams: Team[] = [];
  noTeamPlayers: Player[] = [];

  constructor(
    private messageService: MessageService,
    private playerService: PlayerService,
    private teamService: TeamService
  ) { }

  ngOnInit(): void {
    this.getTeams();
    this.getNoTeamPlayers();
  }

  private getTeams() {
    this.teamService.getTeams()
      .subscribe(teams => this.teams = teams)
  }

  private getNoTeamPlayers() {
    this.playerService.getNoTeamPlayers()
      .subscribe(players => this.noTeamPlayers = players);
  }

    delete(team: Team) {
    this.teams = this.teams.filter(p => p !== team);
    this.teamService.deleteTeam(team.id);
  }

  showCreateForm() {
    if (this.noTeamPlayers.length === 0) {
      this.messageService.add('There are currently no free players to create a new team.\n' +
        'Create new players by going to the players tab');
      return;
    } else {
      this.showFormCreate = true;
    }
  }

  Number(value: string) {
    return parseInt(value);
  }

  add(name: string, playersString: string, commission: number, balance: number) {
    this.messageService.clear();
    name = name.trim();
    playersString = playersString.trim();
    if (!name || !playersString || !commission || !balance) {
      this.messageService.add("All fields must be filled");
      return;
    }
    if (balance < 0) {
      this.messageService.add('The balance of the team cannot be less than 0');
      return;
    }
    if (commission < 0 || commission > 10) {
      this.messageService.add('Commission should be in the range from 0 to 10%');
      return;
    }
    const players = playersString.split(',').map(s => this.Number(s));
    // @ts-ignore
    this.teamService.addTeam({ name, balance, commission , players } as Team)
      .subscribe(team => this.teams.push(team));
    this.showFormCreate = false;
  }

  hideCreateForm() {
    this.showFormCreate = false;
  }
}
