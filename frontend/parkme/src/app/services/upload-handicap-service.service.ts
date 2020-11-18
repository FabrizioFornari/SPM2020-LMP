import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const HANDICAP_UPLOAD_API = 'http://localhost:8080/api/';
const AUTH_TOKEN = `Bearer ${localStorage.getItem('token')}`;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': AUTH_TOKEN }),
};


@Injectable({
  providedIn: 'root'
})
export class UploadHandicapServiceService {

  constructor(private http: HttpClient) { }

  uploadRequest(): Observable<any> {
    return this.http.post(HANDICAP_UPLOAD_API, httpOptions);
  }
}
