import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const HANDICAP_UPLOAD_API = '/api/driver/requestHandicapPermits';

@Injectable({
  providedIn: 'root',
})
export class UploadHandicapServiceService {
  constructor(private http: HttpClient) {}

  getHttpOpt() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('token')}`,
      }),
    };
  }

  uploadRequest(): Observable<any> {
    return this.http.post(HANDICAP_UPLOAD_API, null, this.getHttpOpt());
  }
}
