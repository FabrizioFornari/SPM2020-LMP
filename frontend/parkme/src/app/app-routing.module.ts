import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountInfoComponent } from './pages/account-info/account-info.component';
import { DriverRegistrationComponent } from './pages/driver-registration/driver-registration.component';
import { HandicapPermitsListComponent } from './pages/handicap-permits-list/handicap-permits-list.component';
import { LoginComponent } from './pages/login/login.component';
import { ParkingLotListComponent } from './pages/parking-lot-list/parking-lot-list.component';
import { ParkmanVigRegistrationComponent } from './pages/parkman-vig-registration/parkman-vig-registration.component';
import { UploadHandicapComponent } from './pages/upload-handicap/upload-handicap.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'driver-register', component: DriverRegistrationComponent},
  { path: 'pm_v-register', component: ParkmanVigRegistrationComponent},
  { path: 'account-info', component: AccountInfoComponent},
  { path: 'handicap-upload', component: UploadHandicapComponent},
  { path: 'handicap-download', component: HandicapPermitsListComponent},
  { path: 'parking-lot-list', component: ParkingLotListComponent},
  { path: '', redirectTo: '/parking-lot-list', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
