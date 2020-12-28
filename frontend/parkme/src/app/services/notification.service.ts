import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

import { RxStomp } from '@stomp/rx-stomp';
import * as SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private client: RxStomp;
  public notifications: string[] = [];

  connect() {
    if (!this.client || this.client.connected) {
      this.client = new RxStomp();
      this.client.configure({
        webSocketFactory: () =>
          new SockJS(`http://localhost:8080/notifications`),
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
          const text: string = JSON.parse(response.body).text;
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
}
