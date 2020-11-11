import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const REGISTER_API = 'http://localhost:8080/';
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
    firstName: string;
    lastName: string;
    ssn: string;
    email: string;
    phone: string;
    password: string;
  }): Observable<any> {
    return this.http.post(REGISTER_API, user, httpOptions);
  }

  vRegister(user: {
    firstName: string;
    lastName: string;
    ssn: string;
    email: string;
    phone: string;
    password: string;
  }): Observable<any> {
    return this.http.post(REGISTER_API, user, httpOptions);
  }
}
