import { Component, OnInit } from '@angular/core';
import {Player} from "../player";
import {PlayerService} from "../player.service";
import {MessageService} from "../message.service";

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {
  players: Player[] = [];
  showCreateBlock: boolean = false;

  constructor(
    private playerService: PlayerService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.getPlayers();
  }

  getPlayers(): void {
    this.playerService.getPlayers()
      .subscribe(players => this.players = players);
  }

  add(name: string, age: number, experience: number): void {
    name = name.trim();
    if (!name) {
      this.messageService.add('Player name can`t be blank');
      return;
    }
    if (!age) {
      this.messageService.add('Player age can`t be blank');
      return;
    }
    if (!experience) {
      this.messageService.add('Player experience can`t be blank');
      return;
    }
    if (age < 18) {
      this.messageService.add('The age of the player cannot be less than 18');
      return;
    }
    if (experience < 0) {
      this.messageService.add('The player`s experience cannot be less than 0');
      return;
    }
    this.playerService.addPlayer({ name, age, experience } as Player)
      .subscribe(player => {
        this.players.push(player);
      });
  }

  delete(player: Player): void {
    this.players = this.players.filter(p => p !== player);
    this.playerService.deletePlayer(player.id).subscribe();
  }

  Number(value: string) {
    return parseInt(value);
  }

  showCreateForm() {
    this.messageService.clear();
    this.showCreateBlock = true;
  }

  hideCreateForm() {
    this.showCreateBlock = false;
    this.messageService.clear();
  }
}
