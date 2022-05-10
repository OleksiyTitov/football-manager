import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";
import {MessageService} from "../message.service";
import {ActivatedRoute} from "@angular/router";
import {TeamService} from "../team.service";
import {Team} from "../team";
import {Player} from "../player";
import {PlayerService} from "../player.service";
import {Transfer} from "../transfer";

@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css']
})
export class TeamDetailComponent implements OnInit {
  currentTeam!: Team;
  noTeamPlayers!: Player[];
  showCurrentPlayers: boolean = true;
  showNoTeamPlayers: boolean = false;
  showBuyPlayers: boolean = false;
  invitePlayersIds: number[] = [];
  teams: Team[] = []

  constructor(
    private location: Location,
    private messageService: MessageService,
    private route: ActivatedRoute,
    private teamService: TeamService,
    private playerService: PlayerService
  ) { }

  ngOnInit(): void {
    this.getTeam();
    this.getNoTeamPlayers();
    this.getAllTeams();
  }

  private getAllTeams() {
    this.teamService.getTeams()
      .subscribe(teams => this.teams = teams);
    this.teams = this.teams.filter(p => p !== this.currentTeam);
  }

  private getNoTeamPlayers() {
    this.playerService.getNoTeamPlayers()
      .subscribe(players => this.noTeamPlayers = players);
  }

  private getTeam() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.teamService.getTeam(id)
      .subscribe(team => this.currentTeam = team);
  }

  goBack() {
    this.messageService.clear();
    this.location.back();
  }

  save() {
    this.messageService.clear();
    if (!this.currentTeam.name) {
      this.messageService.add('Team name can`t be blank');
      return;
    }
    if (!this.currentTeam.commission) {
      this.messageService.add('Commission can`t be blank');
      return;
    }
    if (!this.currentTeam.balance) {
      this.messageService.add('Balance can`t be blank');
      return;
    }
    if (this.currentTeam.balance < 0) {
      this.messageService.add('The balance of the team cannot be less than 0');
      return;
    }
    if (this.currentTeam.commission < 0 || this.currentTeam.commission > 10) {
      this.messageService.add('Commission should be in the range from 0 to 10%');
      return;
    }
    const teamWithPlayerIds = this.currentTeam;
    const playerIds = teamWithPlayerIds.players.map((p: { id: number; }) => p.id).concat(this.invitePlayersIds);
    teamWithPlayerIds.players = playerIds;
    this.teamService.updateTeam(teamWithPlayerIds)
      .subscribe(() => this.goBack());
  }

  showPlayersWithNoTeam() {
    this.showCurrentPlayers = false;
    this.showBuyPlayers = false;
    this.showNoTeamPlayers = true;
  }

  showCurrentPlayersList() {
    this.showCurrentPlayers = true;
    this.showBuyPlayers = false;
    this.showNoTeamPlayers = false;
  }

  showBuyPlayersForm() {
    this.showCurrentPlayers = false;
    this.showBuyPlayers = true;
    this.showNoTeamPlayers = false;
  }

  Number(value: string) {
    return parseInt(value);
  }

  buyPlayers(fromId: number, players: string) {
    const playerIds =  players.trim().split(',').map(n => Number(n));
    this.teamService.buyPlayer(this.currentTeam.id, { fromId, playerIds } as Transfer)
      .subscribe();
  }
}
