import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DriverRegistrationValidationService {
  constructor() {}

  validateName(name: string) {
    return name && name.trim() !== '';
  }

  validateSurname(surname: string) {
    return surname && surname.trim() !== '';
  }

  validateSSN(ssn: string) {
    const re = /^(?:[A-Z][AEIOU][AEIOUX]|[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$/i;
    return re.test(String(ssn).toLocaleLowerCase());
  }

  validateEmail(email: string) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
  }

  validatePhone(phone: string) {
    const re = /^((00|\+)39[\. ]??)??3\d{2}[\. ]??\d{6,7}$/;
    return re.test(String(phone));
  }

  validatePlate(plate: string) {
    const re = /[A-Za-z]{2}[0-9]{3}[A-Za-z]{2}/g;
    return re.test(String(plate).toLocaleLowerCase());
  }

  validateVehicle(vehicle: string) {
    return vehicle && vehicle.trim() !== '';
  }

  validatePassword(password: string) {
    return password && password.trim() !== '';
  }
}
