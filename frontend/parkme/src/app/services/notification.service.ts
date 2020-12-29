import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

import { RxStomp } from '@stomp/rx-stomp';
import * as SockJS from 'sockjs-client';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const NOTIFICATION_API = "/api/notification/getAllUserNotifications";

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private client: RxStomp;
  public notifications: string[] = [];

  constructor(private http: HttpClient){}

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
          new SockJS(`/notifications`),
        debug: (msg: string) => console.log(msg),
      });
      this.client.activate();
      this.watchForNotifications();
      console.log('connected!');
    }
  }
  private watchForNotifications() {
    this.client
      .watch('/user/notification/item')
      .pipe(
        map((response: any) => {
          const text: string = JSON.parse(response.body).message;
          console.log('Got ' + text);
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
      this.client.publish({ destination: '/swns/start', headers: {'Authorization':localStorage.getItem('token')} });
    }
  }
  stopNotifications() {
    if (this.client && this.client.connected) {
      this.client.publish({ destination: '/swns/stop' });
    }
  }


  getNotificationFromDB(): Observable<any>{
    return this.http.get(NOTIFICATION_API, this.getHttpOpt());
  }
}
