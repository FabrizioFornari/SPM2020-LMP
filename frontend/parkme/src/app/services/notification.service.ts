import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

import { RxStomp } from '@stomp/rx-stomp';
import * as SockJS from 'sockjs-client';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ToastrService } from 'ngx-toastr';

import {environment} from '../../environments/environment';

const NOTIFICATION_API = environment.baseUrl + "api/notification/getAllUserNotifications";
const NOTIFICATION_READ = environment.baseUrl + "api/notification/setStatusNotification";

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private client: RxStomp;
  public notifications: string[] = [];

  constructor(private http: HttpClient, private toastrService: ToastrService){}


  getHttpOpt(){
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }),
    };
  }

  connect() {
    if (!this.client || this.client.connected) {
      this.client = new RxStomp();
      this.client.configure({
        webSocketFactory: () =>
          new SockJS(environment.baseUrl + 'notifications'),
        debug: (msg: string) => console.log(msg),
      });
      this.client.activate();
      this.watchForNotifications();
      console.log('connected!');
    }
  }
  private watchForNotifications() {
    this.client
      .watch(environment.baseUrl + 'user/notification/item')
      .pipe(
        map((response: any) => {
          const text: string = JSON.parse(response.body).text;
          console.log('Got ' + text);
          this.toastrService.info('You have new notifications');
          return text;
        })
      )
      .subscribe((notification: string) =>
        this.notifications.push(notification)
      );
  }
  disconnect() {
    if (this.client && this.client.connected) {
      this.client.deactivate();
      this.client = null;
      console.log('disconnected :-/');
    }
  }
  startNotifications() {
    if (this.client && this.client.connected) {
      this.client.publish({ destination: environment.baseUrl + 'swns/start', headers: {'Authorization':localStorage.getItem('token')} });
    }
  }
  stopNotifications() {
    if (this.client && this.client.connected) {
      this.client.publish({ destination: environment.baseUrl + 'swns/stop' });
    }
  }


  getNotificationFromDB(): Observable<any>{
    return this.http.get(NOTIFICATION_API, this.getHttpOpt());
  }


  markNotificationRead(id): Observable<any>{
    let body = {
      id: id,
      statusNotification: 'READ'
    }
    return this.http.put(NOTIFICATION_READ, body, this.getHttpOpt());
  }
}
