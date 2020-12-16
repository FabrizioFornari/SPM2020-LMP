import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

import { RxStomp } from "@stomp/rx-stomp";
import * as SockJS from "sockjs-client";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private client: RxStomp;
  public notifications: string[] = [];
  connectClicked() {
    if (!this.client || this.client.connected) {
      this.client = new RxStomp();
      this.client.configure({
        webSocketFactory: () => new SockJS('http://localhost:8080/notifications'),
        debug: (msg: string) => console.log(msg)
      });
      this.client.activate();
      this.watchForNotifications();
      console.info('connected!');
    }
  }
  private watchForNotifications() {
    this.client.watch('/user/notification/item')
      .pipe(
        map((response: any) => {
          const text: string = JSON.parse(response.body).message;
          console.log('Got ' + text);
          return text;
        }))
      .subscribe((notification: string) => this.notifications.push(notification));
  }
  disconnectClicked() {
    if (this.client && this.client.connected) {
      this.client.deactivate();
      this.client = null;
      console.info("disconnected :-/");
    }
  }
  startClicked() {
    if (this.client && this.client.connected) {
      this.client.publish({destination: '/swns/start'});
    }
  }
  stopClicked() {
    if (this.client && this.client.connected) {
      this.client.publish({destination: '/swns/stop'});
    }
  }
}
