import { BrowserModule, Title } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotificationComponent } from './modals/commonModal/notification/notification.component';
import { UpdateEmailComponent } from './modals/driverModal/update-email/update-email.component';
import { UpdatePasswordComponent } from './modals/driverModal/update-password/update-password.component';
import { UpdatePhoneComponent } from './modals/driverModal/update-phone/update-phone.component';
import { UpdateVehicleComponent } from './modals/driverModal/update-vehicle/update-vehicle.component';
import { AdminPanelComponent } from './pages/adminPages/admin-panel/admin-panel.component';
import { LoginComponent } from './pages/commonPages/login/login.component';
import { ProfileComponent } from './pages/commonPages/profile/profile.component';
import { RegisterComponent } from './pages/driverPages/register/register.component';
import { ManagerPanelComponent } from './pages/managerPages/manager-panel/manager-panel.component';
import { BuyTicketComponent } from './pages/driverPages/buy-ticket/buy-ticket.component';
import { VigilantPanelComponent } from './pages/vigilantPages/vigilant-panel/vigilant-panel.component';
import { RegisterVigilantComponent } from './modals/adminModal/register-vigilant/register-vigilant.component';
import { RegisterManagerComponent } from './modals/adminModal/register-manager/register-manager.component';
import { UploadHandicapRequestComponent } from './modals/driverModal/upload-handicap-request/upload-handicap-request.component';
import { HandicapRequestsComponent } from './pages/adminPages/handicap-requests/handicap-requests.component';
import { HandicapRequestComponent } from './modals/adminModal/handicap-request/handicap-request.component';
import { ParkingLotsComponent } from './pages/managerPages/parking-lots/parking-lots.component';
import { AddParkingLotComponent } from './modals/managerModal/add-parking-lot/add-parking-lot.component';
import { OpenParkingLotComponent } from './modals/managerModal/open-parking-lot/open-parking-lot.component';
import { SelectBookingTypeComponent } from './modals/driverModal/select-booking-type/select-booking-type.component';
import { TicketHistoryComponent } from './pages/driverPages/ticket-history/ticket-history.component';
import { MapComponent } from './pages/driverPages/map/map.component';
import { PayTicketComponent } from './modals/driverModal/pay-ticket/pay-ticket.component';
import { LeafletModule } from '@asymmetrik/ngx-leaflet';
import { ConfirmParkingLotBookingComponent } from './modals/driverModal/confirm-parking-lot-booking/confirm-parking-lot-booking.component';
import { CoordinatesModalComponent } from './modals/driverModal/coordinates-modal/coordinates-modal.component';
import { ConfirmPresenceComponent } from './modals/driverModal/confirm-presence/confirm-presence.component';
import { CheckParkComponent } from './modals/commonModal/check-park/check-park.component';
import { RefreshTicketComponent } from './modals/driverModal/refresh-ticket/refresh-ticket.component';
import { ParkInfoComponent } from './modals/vigilantModal/park-info/park-info.component';
import { RefundComponentComponent } from './modals/driverModal/refund-component/refund-component.component';
import { ParkingLotListComponent } from './pages/vigilantPages/parking-lot-list/parking-lot-list.component';
import { BuySubscriptionComponent } from './modals/driverModal/buy-subscription/buy-subscription.component';
import { SelectParkingTypeComponent } from './modals/vigilantModal/select-parking-type/select-parking-type.component';
import { ConfirmSubscriptionComponent } from './modals/driverModal/confirm-subscription/confirm-subscription.component';
import { GenericNotificationComponent } from './modals/commonModal/generic-notification/generic-notification.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    NotificationComponent,
    UpdateVehicleComponent,
    UpdateEmailComponent,
    UpdatePasswordComponent,
    UpdatePhoneComponent,
    ManagerPanelComponent,
    AdminPanelComponent,
    BuyTicketComponent,
    VigilantPanelComponent,
    RegisterVigilantComponent,
    RegisterManagerComponent,
    UploadHandicapRequestComponent,
    HandicapRequestsComponent,
    HandicapRequestComponent,
    ParkingLotsComponent,
    AddParkingLotComponent,
    OpenParkingLotComponent,
    SelectBookingTypeComponent,
    TicketHistoryComponent,
    MapComponent,
    PayTicketComponent,
    ConfirmParkingLotBookingComponent,
    CoordinatesModalComponent,
    ConfirmPresenceComponent,
    CheckParkComponent,
    RefreshTicketComponent,
    ParkInfoComponent,
    RefundComponentComponent,
    ParkingLotListComponent,
    BuySubscriptionComponent,
    SelectParkingTypeComponent,
    ConfirmSubscriptionComponent,
    GenericNotificationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    LeafletModule,
  ],
  providers: [Title],
  bootstrap: [AppComponent],
})
export class AppModule {}
