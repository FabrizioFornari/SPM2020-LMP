import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const HANDLE_REQUEST_API = 'http://localhost:8080/api/';
const AUTH_TOKEN = `Bearer ${localStorage.getItem('token')}`;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': AUTH_TOKEN }),
};


@Injectable({
  providedIn: 'root'
})
export class HandleHandicapRequestsService {

  constructor(private http: HttpClient) { }

  acceptReq(body: { username: any; }): Observable<any> {
    return this.http.post(HANDLE_REQUEST_API, body, httpOptions);
  }

  declineReq(body: { username: any; }): Observable<any> {
    return this.http.post(HANDLE_REQUEST_API, body, httpOptions);
  }
}
