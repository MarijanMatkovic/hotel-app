import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AdminPanelComponent } from '../components/admin-panel/admin-panel.component';
import { GuestViewComponent } from '../components/guest-view/guest-view.component';
import { HousekeeperViewComponent } from '../components/housekeeper-view/housekeeper-view.component';
import { LoginScreenComponent } from '../components/login-screen/login-screen.component';
import { ReceptionistViewComponent } from '../components/receptionist-view/receptionist-view.component';
import { ReservationsComponent } from '../components/reservations/reservations.component';
import { AppComponent } from '../app.component';
import { HomeScreenComponent } from '../components/home-screen/home-screen.component';
import { OwnerPanelComponent } from '../components/owner-panel/owner-panel.component';
import { LoggedInGuard } from '../guards/logged-in.guard';

const routes: Routes = [
  {path: '', component: HomeScreenComponent},
  {path: 'login', component: LoginScreenComponent, canActivate: [LoggedInGuard]},
  {path: 'guest', component: GuestViewComponent},
  {path: 'reservations', component: ReservationsComponent},
  {path: 'admin_panel', component: AdminPanelComponent},
  {path: 'housekeeping', component:HousekeeperViewComponent},
  {path: 'owner', component: OwnerPanelComponent},
  {path: 'receptionist', component: ReceptionistViewComponent}


]


@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
