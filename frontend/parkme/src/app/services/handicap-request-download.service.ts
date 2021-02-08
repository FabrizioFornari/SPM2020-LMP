import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';


const DOWNLOAD_HANDICAP = environment.baseUrl + 'api/admin/handicapPermits/notProcessed';


@Injectable({
  providedIn: 'root'
})
export class HandicapRequestDownloadService {

  constructor(private http: HttpClient) { }

  getHttpOpt(){
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }),
    };
  }

  downloadRequest(): Observable<any> {
    return this.http.get(DOWNLOAD_HANDICAP, this.getHttpOpt());
  }
}