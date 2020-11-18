import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const DOWNLOAD_HANDICAP = 'http://localhost:8080/api/admin/handicapPermits/notProcessed';

const AUTH_TOKEN = `Bearer ${localStorage.getItem('token')}`;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': AUTH_TOKEN }),
};
@Injectable({
  providedIn: 'root'
})
export class HandicapRequestDownloadService {

  constructor(private http: HttpClient) { }

  downloadRequest(): Observable<any> {
    return this.http.get(DOWNLOAD_HANDICAP, httpOptions);
  }
}
