import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const DRIVER_AUTH_API = 'http://localhost:8080/api/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class DriverAuthService {
  constructor(private http: HttpClient) {}

  register(user: {
    firstName: string;
    lastName: string;
    ssn: string;
    email: string;
    phone: number;
    plate: string;
    vehicleType: string;
    password: string;
  }): Observable<any> {
    return this.http.post(
      DRIVER_AUTH_API + 'registration',
      user,
      httpOptions
    );
  }
}
