import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const ACC_MAN_API = 'http://localhost:8080/api/modification/';

@Injectable({
  providedIn: 'root',
})
export class AccountManagementService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }),
    };
  }

  updateEmail(body: {
    currentEmail: string;
    newEmail: string;
  }): Observable<any> {
    return this.http.post(ACC_MAN_API + 'email', body, this.getHttpOpt());
  }

  updatePassword(body: {
    currentPassword: string;
    newPassword: string;
  }): Observable<any> {
    return this.http.post(ACC_MAN_API + 'password', body, this.getHttpOpt());
  }

  updatePhone(body: {
    currentPhone: string;
    newPhone: string;
  }): Observable<any> {
    return this.http.post(ACC_MAN_API + 'phone', body, this.getHttpOpt());
  }

  updateVechiclePlate(body: {
    currentPlate: string;
    newPlate: string;
    currentVehicleType: string;
    newVehicleType: string;
  }): Observable<any> {
    return this.http.post(
      ACC_MAN_API + 'plateAndVehicleType',
      body,
      this.getHttpOpt()
    );
  }
}
