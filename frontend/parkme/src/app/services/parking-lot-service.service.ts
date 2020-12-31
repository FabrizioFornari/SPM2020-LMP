import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Popup, Layer, Content } from 'leaflet';

const PARKING_LOT_API = '/api/parkingManager/';

const DRIVER_STREET_API = "/api/driver/streets";
const DRIVER_PARKS_API = "/api/parkingManager/parkingLots/getStreet"
const DRIVER_BOOKING = "/api/driver/setStatusBooked";
const DRIVER_CURRENT_BOOKING = "/api/driver/booking";
const DRIVER_NEAREST_PARKING = "/api/driver/nearestParkingLot";
const DRIVER_CANCEL = "/api/driver/deleteBooking";
const DRIVER_HISTORY = "/api/driver/getAllTicketParkingLot";
const DRIVER_BUY = "/api/driver/createParkingLotTicket";


const CHANGE_PARKING_LOT = "/api/driver/changeParkingLot";

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


  driverGetStreetList(): Observable<any>{
    return this.http.get(DRIVER_STREET_API, this.getHttpOpt());
  }

  driverGetParkingLots(street: string | HTMLElement | Popup | ((layer: Layer) => Content)): Observable<any>{
    return this.http.get(DRIVER_PARKS_API + `?street=${street}`, this.getHttpOpt());
  }

  driverBookParkingLot(parkingLot: any): Observable<any>{
    return this.http.put(DRIVER_BOOKING, parkingLot, this.getHttpOpt());
  }

  driverGetCurrentBooking(): Observable<any>{
    return this.http.get(DRIVER_CURRENT_BOOKING, this.getHttpOpt());
  }

  driverGetNearestParkingLot(lat: number, lng: number): Observable<any>{
    return this.http.get(`${DRIVER_NEAREST_PARKING}?latitude=${lat}&longitude=${lng}`, this.getHttpOpt());
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

}
