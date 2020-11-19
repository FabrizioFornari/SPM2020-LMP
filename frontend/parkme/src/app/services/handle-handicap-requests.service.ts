import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const HANDLE_REQUEST_API = 'http://localhost:8080/api/admin/setting/handicapPermits';
const AUTH_TOKEN = `Bearer ${localStorage.getItem('token')}`;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': AUTH_TOKEN }),
};


@Injectable({
  providedIn: 'root'
})
export class HandleHandicapRequestsService {

  constructor(private http: HttpClient) { }

  acceptReq(req: { username: string; }): Observable<any> {
    let body = {
      username: req.username,
      isAccepted: true
    }
    return this.http.post(HANDLE_REQUEST_API, body, httpOptions);
  }

  declineReq(req: { username: string; }): Observable<any> {
    let body = {
      username: req.username,
      isAccepted: false
    }
    return this.http.post(HANDLE_REQUEST_API, body, httpOptions);
  }
}
