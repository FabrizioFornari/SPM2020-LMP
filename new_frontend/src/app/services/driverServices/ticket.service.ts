import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Content } from '@angular/compiler/src/render3/r3_ast';
import { Injectable } from '@angular/core';
import { Popup, Layer } from 'leaflet';
import { Observable } from 'rxjs';

const DRIVER_CURRENT_BOOKING = 'http://localhost:8080/api/driver/booking';
const DRIVER_HISTORY =
  'http://localhost:8080/api/driver/getAllTicketParkingLot';
const DRIVER_CANCEL = 'http://localhost:8080/api/driver/deleteBooking';
const DRIVER_BUY = 'http://localhost:8080/api/driver/createParkingLotTicket';
const DRIVER_STREET_API = 'http://localhost:8080/api/driver/streets';
const DRIVER_PARKS_API =
  'http://localhost:8080/api/parkingManager/parkingLots/getStreet';
const DRIVER_BOOKING = 'http://localhost:8080/api/driver/setStatusBooked';
const DRIVER_NEAREST_PARKING =
  'http://localhost:8080/api/driver/nearestParkingLot';

const DRIVER_CHANGE_PARKING_LOT =
  'http://localhost:8080/api/driver/changeParkingLot';

const DRIVER_REFRESH_TICKET = 'http://localhost:8080/api/driver/refreshTicket';

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
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

  driverGetStreetList(): Observable<any> {
    return this.http.get(DRIVER_STREET_API, this.getHttpOpt());
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
}
