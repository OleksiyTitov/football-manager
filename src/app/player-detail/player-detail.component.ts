import { Component, OnInit } from '@angular/core';
import {Player} from "../player";
import {ActivatedRoute} from "@angular/router";
import {PlayerService} from "../player.service";
import {Location} from "@angular/common";
import {MessageService} from "../message.service";

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {
   player! : Player;

  constructor(
    private route: ActivatedRoute,
    private playerService: PlayerService,
    private messageService: MessageService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.getPlayer();
  }

  goBack() {
    this.messageService.clear();
    this.location.back();
  }

  save() {
    this.messageService.clear();
    if (!this.player.name) {
      this.messageService.add('Player name can`t be blank');
      return;
    }
    if (!this.player.age) {
      this.messageService.add('Player age can`t be blank');
      return;
    }
    if (!this.player.experience) {
      this.messageService.add('Player experience can`t be blank');
      return;
    }
    if (this.player.age < 18) {
      this.messageService.add('The age of the player cannot be less than 18');
      return;
    }
    if (this.player.experience < 0) {
      this.messageService.add('The player`s experience cannot be less than 0');
      return;
    }
    this.messageService.clear();
    this.playerService.updatePlayer(this.player)
      .subscribe(() => this.goBack());
  }

  private getPlayer() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.playerService.getPlayer(id)
      .subscribe(player => this.player = player);
  }
}
