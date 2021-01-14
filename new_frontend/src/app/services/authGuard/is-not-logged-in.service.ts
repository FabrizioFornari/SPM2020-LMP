import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class IsNotLoggedInService {
  constructor(private router: Router) {}
  canActivate() {
    if (this.notLoggedIn()) {
      return true;
    } else {
      this.router.navigate(['profile']);
      return false;
    }
  }

  notLoggedIn() {
    const jwtHelper: JwtHelperService = new JwtHelperService();
    const token = localStorage.getItem('token');
    return jwtHelper.isTokenExpired(token);
  }
}
