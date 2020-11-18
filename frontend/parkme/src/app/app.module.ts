import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DriverRegistrationComponent } from './pages/driver-registration/driver-registration.component';
import { ParkmanVigRegistrationComponent } from './pages/parkman-vig-registration/parkman-vig-registration.component';
import { AccountInfoComponent } from './pages/account-info/account-info.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UpdateEmailComponent } from './modal/update-email/update-email.component';
import { UpdatePhoneComponent } from './modal/update-phone/update-phone.component';
import { UpdatePasswordComponent } from './modal/update-password/update-password.component';
import { UpdateVehiclePlateComponent } from './modal/update-vehicle-plate/update-vehicle-plate.component';
import { UploadHandicapComponent } from './pages/upload-handicap/upload-handicap.component';
import { HandicapPermitsListComponent } from './pages/handicap-permits-list/handicap-permits-list.component';

@NgModule({
  declarations: [AppComponent, LoginComponent, DriverRegistrationComponent, ParkmanVigRegistrationComponent, AccountInfoComponent, UpdateEmailComponent, UpdatePhoneComponent, UpdatePasswordComponent, UpdateVehiclePlateComponent, UploadHandicapComponent, HandicapPermitsListComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot({
      timeOut: 1500,
      preventDuplicates: true,
      positionClass: 'toast-top-right',
    }),
    BrowserAnimationsModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
