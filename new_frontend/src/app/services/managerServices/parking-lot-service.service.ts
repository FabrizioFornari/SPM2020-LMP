import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const PARKING_LOT_API = environment.baseUrl + 'api/parkingManager/';

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

  getAllParkingLots(): Observable<any> {
    return this.http.get(
      PARKING_LOT_API + 'parkingLots/all',
      this.getHttpOpt()
    );
  }

  addParkingLot(body: {
    street: string;
    numberOfParkingLot: number;
    isHandicapParkingLot: boolean;
    pricePerHour: number;
    typeOfVehicle: string;
    coordinates: { latitude: string; longitude: string };
  }): Observable<any> {
    console.log(body);
    return this.http.post(
      PARKING_LOT_API + 'parkingLot/create',
      body,
      this.getHttpOpt()
    );
  }

  updateParkingLot(body: {
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

  deleteParkingLot(body: {
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
