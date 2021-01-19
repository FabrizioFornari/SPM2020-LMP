import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Content } from "@angular/compiler/src/render3/r3_ast";
import { Injectable } from "@angular/core";
import { Popup, Layer } from "leaflet";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

const DRIVER_CURRENT_BOOKING = environment.baseUrl + "api/driver/booking";
const DRIVER_HISTORY =
  environment.baseUrl + "api/driver/getAllTicketParkingLot";
const DRIVER_CANCEL = environment.baseUrl + "api/driver/deleteBooking";
const DRIVER_BUY = environment.baseUrl + "api/driver/createParkingLotTicket";
const DRIVER_STREET_API = environment.baseUrl + "api/driver/streets";
const DRIVER_PARKS_API =
  environment.baseUrl + "api/parkingManager/parkingLots/getStreet";
const DRIVER_BOOKING = environment.baseUrl + "api/driver/setStatusBooked";
const DRIVER_NEAREST_PARKING =
  environment.baseUrl + "api/driver/nearestParkingLot";

const DRIVER_CHANGE_PARKING_LOT =
  environment.baseUrl + "api/driver/changeParkingLot";

const DRIVER_REFRESH_TICKET = environment.baseUrl + "api/driver/refreshTicket";

const DRIVER_CURRENT_SUBSCRIPTION =
  environment.baseUrl + "api/driver/getCurrentSubscription";

const DRIVER_GET_PERSONAL_LOTS =
  environment.baseUrl + "api/driver/availablePersonalParkingLots";

const DRIVER_BUY_SUBSCRIPTION =
  environment.baseUrl + "api/driver/createPersonalParkingLotSubscription";
@Injectable({
  providedIn: "root",
})
export class TicketService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      }),
    };
  }

  driverGetCurrentBooking(): Observable<any> {
    return this.http.get(DRIVER_CURRENT_BOOKING, this.getHttpOpt());
  }

  driverGetTicketHistory(): Observable<any> {
    return this.http.get(DRIVER_HISTORY, this.getHttpOpt());
  }

  driverCancelBooking(): Observable<any> {
    return this.http.delete(DRIVER_CANCEL, this.getHttpOpt());
  }

  driverBuyTicket(body): Observable<any> {
    return this.http.post(DRIVER_BUY, body, this.getHttpOpt());
  }

  driverGetStreetList(personal: boolean): Observable<any> {
    return this.http.get(
      DRIVER_STREET_API + `?personal=${personal}`,
      this.getHttpOpt()
    );
  }

  driverGetParkingLots(
    street: string | HTMLElement | Popup | ((layer: Layer) => Content)
  ): Observable<any> {
    return this.http.get(
      DRIVER_PARKS_API + `?street=${street}`,
      this.getHttpOpt()
    );
  }

  driverBookParkingLot(parkingLot: any): Observable<any> {
    return this.http.put(DRIVER_BOOKING, parkingLot, this.getHttpOpt());
  }

  driverGetNearestParkingLot(lat: number, lng: number): Observable<any> {
    return this.http.get(
      `${DRIVER_NEAREST_PARKING}?latitude=${lat}&longitude=${lng}`,
      this.getHttpOpt()
    );
  }

  driverChangeParkingLot(): Observable<any> {
    return this.http.get(DRIVER_CHANGE_PARKING_LOT, this.getHttpOpt());
  }

  driverRefreshTicket(body): Observable<any> {
    return this.http.put(DRIVER_REFRESH_TICKET, body, this.getHttpOpt());
  }

  driverGetCurrentSubscription(): Observable<any> {
    return this.http.get(DRIVER_CURRENT_SUBSCRIPTION, this.getHttpOpt());
  }

  driverGetPersonalParkingLots(street: string): Observable<any> {
    return this.http.get(
      DRIVER_GET_PERSONAL_LOTS + `?street=${street}`,
      this.getHttpOpt()
    );
  }

  driverBuySubscription(body): Observable<any> {
    return this.http.post(DRIVER_BUY_SUBSCRIPTION, body, this.getHttpOpt());
  }
}
