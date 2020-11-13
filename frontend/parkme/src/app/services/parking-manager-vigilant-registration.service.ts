import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const PM_REGISTER_API = 'http://localhost:8080/api/parkingmanager/registration';
const VG_REGISTER_API = 'http://localhost:8080/api/vigilant/registration';
const AUTH_TOKEN = `Bearer ${localStorage.getItem('token')}`;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': AUTH_TOKEN }),
};

@Injectable({
  providedIn: 'root',
})
export class ParkingManagerVigilantRegistrationService {
  constructor(private http: HttpClient) {}

  pmRegister(user: {
    username: string;
    firstName: string;
    lastName: string;
    ssn: string;
    email: string;
    phone: string;
    password: string;
  }): Observable<any> {
    return this.http.post(PM_REGISTER_API, user, httpOptions);
  }

  vRegister(user: {
    username: string;
    firstName: string;
    lastName: string;
    ssn: string;
    email: string;
    phone: string;
    password: string;
  }): Observable<any> {
    return this.http.post(VG_REGISTER_API, user, httpOptions);
  }
}
