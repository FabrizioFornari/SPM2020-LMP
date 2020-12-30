import { Component, OnInit } from '@angular/core';

import { NotificationService } from 'src/app/services/notification.service';
import { UnifiedLoginService } from './services/unified-login.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'parkme';

  constructor(
    private unifiedlogin: UnifiedLoginService,
    private notificationService: NotificationService
  ) {}

  ngOnInit() {
    if (this.loggedIn) {
      this.notificationService.connect();
      this.notificationService.startNotifications();
    } else {
      return null;
    }
  }

  loggedIn() {
    const token = localStorage.getItem('token');
    const jwtHelper: JwtHelperService = new JwtHelperService();
    return jwtHelper.isTokenExpired(token);
  }
}
