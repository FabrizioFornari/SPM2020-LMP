import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Popup, Layer, Content } from 'leaflet';
import { environment } from 'src/environments/environment';

const PARKING_LOT_API = environment.baseUrl + 'api/parkingManager/';

const DRIVER_STREET_API = environment.baseUrl +'api/driver/streets';
const DRIVER_PARKS_API = environment.baseUrl +'api/parkingManager/parkingLots/getStreet';
const DRIVER_BOOKING = environment.baseUrl +'api/driver/setStatusBooked';
const DRIVER_CURRENT_BOOKING = environment.baseUrl +'api/driver/booking';
const DRIVER_NEAREST_PARKING = environment.baseUrl +'api/driver/nearestParkingLot';
const DRIVER_CANCEL = environment.baseUrl +'api/driver/deleteBooking';
const DRIVER_HISTORY = environment.baseUrl +'api/driver/getAllTicketParkingLot';
const DRIVER_BUY = environment.baseUrl +'api/driver/createParkingLotTicket';
const VIGILANT_GET_STREETS = environment.baseUrl +'api/vigilant/getAllStreet';
const VIGILANT_GET_PARKS_STREET = environment.baseUrl +'api/vigilant/getParkingLots/street';
const VIGILANT_GET_PARK_INFO = environment.baseUrl +'api/vigilant/getParkingLot';
const VIGILANT_SET_PARK_DISABLED = environment.baseUrl +'api/vigilant/setStatusParkingLotDisabled';
const VIGILANT_SET_PARK_ENABLED = environment.baseUrl +'api/vigilant/setStatusParkingLotEnabled';

const CHANGE_PARKING_LOT = environment.baseUrl + 'api/driver/changeParkingLot';

@Injectable({
  providedIn: 'root',
})
export class ParkingLotServiceService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }),
    };
  }

  getList(): Observable<any> {
    return this.http.get(
      PARKING_LOT_API + 'parkingLots/all',
      this.getHttpOpt()
    );
  }

  insertParking(body: {
    street: string;
    numberOfParkingLot: number;
    isHandicapParkingLot: boolean;
    pricePerHours: number;
    typeOfVehicle: string;
    coordinates: { latitude: string; longitude: string };
  }): Observable<any> {
    return this.http.post(
      PARKING_LOT_API + 'parkingLot/create',
      body,
      this.getHttpOpt()
    );
  }

  updateParking(body: {
    newStreet: string;
    newNumberOfParkingLot: number;
    newIsHandicapParkingLot: boolean;
    newPricePerHours: number;
    newTypeOfVehicle: string;
    newLatitude: string;
    newLongitude: string;
    oldStreet: string;
    oldNumberOfParkingLot: number;
  }): Observable<any> {
    return this.http.put(
      PARKING_LOT_API + 'parkingLot/update',
      body,
      this.getHttpOpt()
    );
  }

  deleteParking(body: {
    street: string;
    numberOfParkingLot: number;
  }): Observable<any> {
    return this.http.delete(
      PARKING_LOT_API +
        `parkingLot/delete?street=${body.street}&numberOfParkingLot=${body.numberOfParkingLot}`,
      this.getHttpOpt()
    );
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

  driverGetCurrentBooking(): Observable<any> {
    return this.http.get(DRIVER_CURRENT_BOOKING, this.getHttpOpt());
  }

  driverGetNearestParkingLot(lat: number, lng: number): Observable<any> {
    return this.http.get(
      `${DRIVER_NEAREST_PARKING}?latitude=${lat}&longitude=${lng}`,
      this.getHttpOpt()
    );
  }

  driverCancelBooking(): Observable<any> {
    return this.http.delete(DRIVER_CANCEL, this.getHttpOpt());
  }

  driverGetTicketHistory(): Observable<any> {
    return this.http.get(DRIVER_HISTORY, this.getHttpOpt());
  }

  driverBuyTicket(body): Observable<any> {
    return this.http.post(DRIVER_BUY, body, this.getHttpOpt());
  }

  driverChangeParkingLot(): Observable<any> {
    return this.http.get(CHANGE_PARKING_LOT, this.getHttpOpt());
  }

  vigilantGetStreetList(): Observable<any> {
    return this.http.get(VIGILANT_GET_STREETS, this.getHttpOpt());
  }

  vigilantGetParksStreet(street): Observable<any> {
    return this.http.get(
      VIGILANT_GET_PARKS_STREET + `?street=${street}`,
      this.getHttpOpt()
    );
  }

  vigilantGetParkInfo(street: string, num: number): Observable<any> {
    return this.http.get(
      VIGILANT_GET_PARK_INFO + `?street=${street}&numberOfParkingLot=${num}`,
      this.getHttpOpt()
    );
  }

  vigilantSetParkDisabled(body): Observable<any> {
    return this.http.put(VIGILANT_SET_PARK_DISABLED, body, this.getHttpOpt());
  }
  vigilantSetParkEnabled(body): Observable<any> {
    return this.http.put(VIGILANT_SET_PARK_ENABLED, body, this.getHttpOpt());
  }
}
