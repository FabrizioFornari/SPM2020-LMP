import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const DRIVER_AUTH_API = '/api/driver/registration';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class DriverAuthService {
  constructor(private http: HttpClient) {}

  register(user: {
    username: string;
    firstName: string;
    lastName: string;
    ssn: string;
    email: string;
    phone: string;
    plate: string;
    vehicleType: string;
    password: string;
  }): Observable<any> {
    return this.http.post(DRIVER_AUTH_API, user, httpOptions);
  }
}
