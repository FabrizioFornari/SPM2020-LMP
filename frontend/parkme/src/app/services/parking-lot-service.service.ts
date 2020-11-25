import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const PARKING_LOT_API = 'http://localhost:8080/api/parkingManager/';

@Injectable({
  providedIn: 'root'
})
export class ParkingLotServiceService {

  constructor(private http: HttpClient) { }

  getHttpOpt(){
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }),
    };
  }


  getList(): Observable<any> {
    return this.http.get(PARKING_LOT_API + "parkingLots/all" , this.getHttpOpt());
  }
}
