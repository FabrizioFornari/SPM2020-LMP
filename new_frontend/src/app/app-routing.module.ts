import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminPanelComponent } from './pages/adminPages/admin-panel/admin-panel.component';
import { HandicapRequestsComponent } from './pages/adminPages/handicap-requests/handicap-requests.component';
import { LoginComponent } from './pages/commonPages/login/login.component';
import { ProfileComponent } from './pages/commonPages/profile/profile.component';
import { BuyTicketComponent } from './pages/driverPages/buy-ticket/buy-ticket.component';
import { MapComponent } from './pages/driverPages/map/map.component';
import { RegisterComponent } from './pages/driverPages/register/register.component';
import { TicketHistoryComponent } from './pages/driverPages/ticket-history/ticket-history.component';
import { ManagerPanelComponent } from './pages/managerPages/manager-panel/manager-panel.component';
import { ParkingLotsComponent } from './pages/managerPages/parking-lots/parking-lots.component';
import { ParkingLotListComponent } from './pages/vigilantPages/parking-lot-list/parking-lot-list.component';
import { VigilantPanelComponent } from './pages/vigilantPages/vigilant-panel/vigilant-panel.component';
import { IsAdminService } from './services/authGuard/is-admin.service';
import { IsDriverService } from './services/authGuard/is-driver.service';
import { IsLoggedInService } from './services/authGuard/is-logged-in.service';
import { IsManagerService } from './services/authGuard/is-manager.service';
import { IsNotLoggedInService } from './services/authGuard/is-not-logged-in.service';
import { IsVigilantService } from './services/authGuard/is-vigilant.service';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [IsNotLoggedInService],
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [IsNotLoggedInService],
  },
  {
    path: 'manager-panel',
    component: ManagerPanelComponent,
    canActivate: [IsManagerService],
  },
  {
    path: 'manager-panel/parking-lots',
    component: ParkingLotsComponent,
    canActivate: [IsManagerService],
  },
  {
    path: 'admin-panel',
    component: AdminPanelComponent,
    canActivate: [IsAdminService],
  },
  {
    path: 'admin-panel/handicap-requests',
    component: HandicapRequestsComponent,
    canActivate: [IsAdminService],
  },
  {
    path: 'vigilant-panel',
    component: VigilantPanelComponent,
    canActivate: [IsVigilantService],
  },
  {
    path: 'vigilant-panel/parking-lot-list',
    component: ParkingLotListComponent,
    canActivate: [IsVigilantService],
  },
  {
    path: 'buy-ticket',
    component: BuyTicketComponent,
    canActivate: [IsDriverService],
  },
  {
    path: 'driver/ticket-history',
    component: TicketHistoryComponent,
    canActivate: [IsDriverService],
  },
  {
    path: 'map',
    component: MapComponent,
    canActivate: [IsDriverService],
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [IsLoggedInService],
  },
  { path: '', redirectTo: 'profile', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
