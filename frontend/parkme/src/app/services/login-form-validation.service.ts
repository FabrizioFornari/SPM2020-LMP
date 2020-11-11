import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginFormValidationService {
  constructor() {}

  validateEmail(email: string) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return true ? re.test(String(email).toLowerCase()) : false;
  }

  validatePassword(password: string) {
    return password !== null && password.trim() !== '';
  }
}
