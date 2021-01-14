import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const DRIVER_REGISTER_ENDPOINT =
  'http://localhost:8080/api/driver/registration';

@Injectable({
  providedIn: 'root',
})
export class DriverRegisterService {
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
    return this.http.post(DRIVER_REGISTER_ENDPOINT, user);
  }
}
