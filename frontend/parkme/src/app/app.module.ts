import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DriverRegistrationComponent } from './pages/driver-registration/driver-registration.component';
import { ParkmanVigRegistrationComponent } from './pages/parkman-vig-registration/parkman-vig-registration.component';
import { AccountInfoComponent } from './pages/account-info/account-info.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, DriverRegistrationComponent, ParkmanVigRegistrationComponent, AccountInfoComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot({
      timeOut: 1500,
      preventDuplicates: true,
      positionClass: 'toast-top-right',
    }),
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
