import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const DOWNLOAD_HANDICAP =
  'http://localhost:8080/api/admin/handicapPermits/notProcessed';

const HANDLE_REQUEST_API =
  'http://localhost:8080/api/admin/setting/handicapPermits';

@Injectable({
  providedIn: 'root',
})
export class HandicapRequestService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }),
    };
  }

  downloadRequest(): Observable<any> {
    return this.http.get(DOWNLOAD_HANDICAP, this.getHttpOpt());
  }

  acceptRequest(username): Observable<any> {
    let body = {
      username: username,
      isAccepted: true,
    };
    return this.http.post(HANDLE_REQUEST_API, body, this.getHttpOpt());
  }

  rejectRequest(username): Observable<any> {
    let body = {
      username: username,
      isAccepted: false,
    };
    return this.http.post(HANDLE_REQUEST_API, body, this.getHttpOpt());
  }
}
