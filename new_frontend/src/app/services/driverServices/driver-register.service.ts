import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../../environments/environment';

const DRIVER_REGISTER_ENDPOINT =
environment.baseUrl + 'api/driver/registration';

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
