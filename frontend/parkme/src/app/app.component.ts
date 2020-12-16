import { Component, OnInit } from '@angular/core';

import { NotificationService } from "src/app/services/notification.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'parkme';

  constructor(private notification: NotificationService){}

  ngOnInit(){
    this.notification.connect();
    this.notification.startNotifications();
  }
}
