import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class IsDriverService implements CanActivate {
  constructor(private router: Router) {}
  canActivate() {
    if (this.isDriver()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      return false;
    }
  }

  isDriver() {
    const jwtHelper: JwtHelperService = new JwtHelperService();
    const token = localStorage.getItem('token');
    if (!jwtHelper.isTokenExpired(token)) {
      const role = JSON.parse(localStorage.getItem('user')).roles;
      if (role == 'ROLE_DRIVER') {
        return true;
      } else return false;
    } else return false;
  }
}
