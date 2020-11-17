import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AccountManagementValidatorService {
  constructor() {}

  validateEmail(email: string) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return true ? re.test(String(email).toLowerCase()) : false;
  }

  validatePhone(phone: string) {
    const re = /^((00|\+)39[\. ]??)??3\d{2}[\. ]??\d{6,7}$/;
    return true ? re.test(String(phone)) : false;
  }

  validatePlate(plate: string) {
    const re = /[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}/g;
    return true ? re.test(String(plate).toLowerCase()) : false;
  }

  validateVehicle(vehicle: string) {
    return vehicle !== null && vehicle.trim() !== '';
  }

  validatePassword(password: string) {
    return password!== null  && password.trim() !== '';
  }
}
