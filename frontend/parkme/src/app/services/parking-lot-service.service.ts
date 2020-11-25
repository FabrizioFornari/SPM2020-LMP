import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const PARKING_LOT_API = 'http://localhost:8080/api/parkingManager/';

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
}
