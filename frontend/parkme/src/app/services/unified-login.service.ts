import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const UNIFIED_LOGIN_API = environment.baseUrl + 'api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class UnifiedLoginService {
  
  constructor(private http: HttpClient) {}

  login(user: { email: string; password: string }): Observable<any> {
    return this.http.post(UNIFIED_LOGIN_API + 'login', user, httpOptions);
  }
}
