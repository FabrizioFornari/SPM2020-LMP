import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';

const HANDLE_REQUEST_API = environment.baseUrl + 'api/admin/setting/handicapPermits';



@Injectable({
  providedIn: 'root'
})
export class HandleHandicapRequestsService {

  constructor(private http: HttpClient) { }

  getHttpOpt(){
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }),
    };
  }

  acceptReq(req: { username: string; }): Observable<any> {
    let body = {
      username: req.username,
      isAccepted: true
    }
    return this.http.post(HANDLE_REQUEST_API, body, this.getHttpOpt());
  }

  declineReq(req: { username: string; }): Observable<any> {
    let body = {
      username: req.username,
      isAccepted: false
    }
    return this.http.post(HANDLE_REQUEST_API, body, this.getHttpOpt());
  }
}
