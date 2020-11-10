import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DriverRegistrationValidationService {

  constructor() { }

  validateName(name: string){}

  validateSurname(surname: string){}

  validateSSN(ssn: string){}

  validateEmail(email: string) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  validatePhone(phone: number){}

  validatePlate(plate: string){}


  validateVehicle(vehicle: string){}

  validatePassword(password: string) {
    return (password && password.trim()!== "");
  }
}
