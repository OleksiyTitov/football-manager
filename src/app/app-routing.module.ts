import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PlayersComponent} from "./players/players.component";
import {PlayerDetailComponent} from "./player-detail/player-detail.component";
import {TeamsComponent} from "./teams/teams.component";
import {TeamDetailComponent} from "./team-detail/team-detail.component";

const routes: Routes = [
  { path: '', redirectTo: '/teams', pathMatch: 'full' },
  { path: 'players', component: PlayersComponent },
  { path: 'teams', component: TeamsComponent },
  { path: 'players/:id', component: PlayerDetailComponent },
  { path: 'teams/:id', component: TeamDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
