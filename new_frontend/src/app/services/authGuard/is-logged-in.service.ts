import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class IsLoggedInService implements CanActivate {
  constructor(private router: Router) {}
  canActivate() {
    if (this.notLoggedIn()) {
      this.router.navigate(['login']);
      return false;
    } else {
      return true;
    }
  }

  notLoggedIn() {
    const jwtHelper: JwtHelperService = new JwtHelperService();
    const token = localStorage.getItem('token');
    return jwtHelper.isTokenExpired(token);
  }
}
