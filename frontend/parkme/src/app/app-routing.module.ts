import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AccountInfoComponent } from './pages/account-info/account-info.component';
import { DriverRegistrationComponent } from './pages/driver-registration/driver-registration.component';
import { LoginComponent } from './pages/login/login.component';
import { ParkmanVigRegistrationComponent } from './pages/parkman-vig-registration/parkman-vig-registration.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'driver-register', component: DriverRegistrationComponent},
  { path: 'pm_v-register', component: ParkmanVigRegistrationComponent},
  { path: 'account-info', component: AccountInfoComponent},
  { path: '', redirectTo: '/account-info', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
