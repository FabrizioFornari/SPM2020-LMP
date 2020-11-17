import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const ACC_MAN_API = 'http://localhost:8080/api/modification/';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem('token')}`,
  }),
};

@Injectable({
  providedIn: 'root',
})
export class AccountManagementService {
  constructor(private http: HttpClient) {}

  updateEmail(body: {
    currentEmail: string;
    newEmail: string;
  }): Observable<any> {
    return this.http.post(ACC_MAN_API + 'email', body, httpOptions);
  }

  updatePassword(body: {
    currentPassword: string;
    newPassword: string;
  }): Observable<any> {
    return this.http.post(ACC_MAN_API + 'password', body, httpOptions);
  }

  updatePhone(body: {
    currentPhone: string;
    newPhone: string;
  }): Observable<any> {
    return this.http.post(ACC_MAN_API + 'phone', body, httpOptions);
  }
}
