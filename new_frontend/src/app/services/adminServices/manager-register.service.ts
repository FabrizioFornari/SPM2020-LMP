import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const PM_REGISTER_API =
  'http://localhost:8080/api/admin/registration/parkingManager';

@Injectable({
  providedIn: 'root',
})
export class ManagerRegisterService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }),
    };
  }

  pmRegister(user: {
    username: string;
    firstName: string;
    lastName: string;
    ssn: string;
    email: string;
    phone: string;
    password: string;
  }): Observable<any> {
    return this.http.post(PM_REGISTER_API, user, this.getHttpOpt());
  }
}
