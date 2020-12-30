import { Component, OnInit } from '@angular/core';

import { NotificationService } from "src/app/services/notification.service";
import { UnifiedLoginService } from './services/unified-login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'parkme';

  constructor(private unifiedlogin: UnifiedLoginService, private notificationService: NotificationService){}

  ngOnInit(){
    this.unifiedlogin.loggedIn$.subscribe((value: boolean)=> {
      if (value) {
        this.notificationService.connect();
          this.notificationService.startNotifications();
      } else {
        return null;
      }
    })
  }
}
