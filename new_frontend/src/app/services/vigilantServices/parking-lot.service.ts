import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const VIGILANT_SET_PARK_DISABLED =
  'http://localhost:8080/api/vigilant/setStatusParkingLotDisabled';
const VIGILANT_SET_PARK_ENABLED =
  'http://localhost:8080/api/vigilant/setStatusParkingLotEnabled';
const VIGILANT_GET_PARK_INFO =
  'http://localhost:8080/api/vigilant/getParkingLot';

const VIGILANT_GET_STREETS = 'http://localhost:8080/api/vigilant/getAllStreet';
const VIGILANT_GET_PARKS_STREET =
  'http://localhost:8080/api/vigilant/getParkingLots/street';

@Injectable({
  providedIn: 'root',
})
export class ParkingLotService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }),
    };
  }

  vigilantSetParkDisabled(body): Observable<any> {
    return this.http.put(VIGILANT_SET_PARK_DISABLED, body, this.getHttpOpt());
  }
  vigilantSetParkEnabled(body): Observable<any> {
    return this.http.put(VIGILANT_SET_PARK_ENABLED, body, this.getHttpOpt());
  }
  vigilantGetParkInfo(street: string, num: number): Observable<any> {
    return this.http.get(
      VIGILANT_GET_PARK_INFO + `?street=${street}&numberOfParkingLot=${num}`,
      this.getHttpOpt()
    );
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
}
