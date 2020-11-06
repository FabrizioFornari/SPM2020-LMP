import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DriverRegistrationComponent } from './pages/driver-registration/driver-registration.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'driver-register', component: DriverRegistrationComponent},
  { path: '', redirectTo: '/driver-register', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
