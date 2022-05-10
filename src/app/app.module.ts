import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PlayersComponent } from './players/players.component';
import { MessagesComponent } from './messages/messages.component';
import { PlayerDetailComponent } from './player-detail/player-detail.component';
import { AppRoutingModule } from './app-routing.module';
import { TeamsComponent } from './teams/teams.component';
import { TeamDetailComponent } from './team-detail/team-detail.component';

@NgModule({
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    PlayersComponent,
    MessagesComponent,
    PlayerDetailComponent,
    TeamsComponent,
    TeamDetailComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
