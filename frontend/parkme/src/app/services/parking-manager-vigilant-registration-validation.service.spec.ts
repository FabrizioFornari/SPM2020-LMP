import { TestBed } from '@angular/core/testing';

import { ParkingManagerVigilantRegistrationValidationService } from './parking-manager-vigilant-registration-validation.service';

describe('ParkingManagerVigilantRegistrationValidationService', () => {
  let service: ParkingManagerVigilantRegistrationValidationService;
  

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParkingManagerVigilantRegistrationValidationService);
  });

  //validateAccount FALSE
  it('account validation should be false', () => {
    expect(service.validateAccount(" ")).toBe(false);
  })

  //validateAccount TRUE
  it('account validation should be true', () => {
    expect(service.validateAccount("Vigilant")).toBe(true);
  })

  //validateName FALSE
  it('name validation should be false', () => {
    expect(service.validateName(" ")).toBe(false);
  })

  //validateName TRUE
  it('name validation should be true', () => {
    expect(service.validateName("Mario")).toBe(true);
  })

  //validateSurname FALSE
  it('surname validation should be false', () => {
    expect(service.validateSurname(" ")).toBe(false);
  })

  //validateSurname TRUE
  it('surname validation should be true', () => {
    expect(service.validateSurname("Rossi")).toBe(true);
  })

  //validateSSN FALSE
  it('ssn validation should be false', () => {
    expect(service.validateSSN("asmdsfds")).toBe(false);
  })

  //validateSSN TRUE
  it('ssn validation should be true', () => {
    expect(service.validateSSN("RSSMRA80A01F205X")).toBe(true);
  })

  //validateEmail FALSE
  it('email validation should be false', () => {
    expect(service.validateEmail("mario@rossi")).toBe(false);
  })

  //validateEmail TRUE
  it('email validation should be true', () => {
    expect(service.validateEmail("mario@rossi.it")).toBe(true);
  })

  //validatePhone FALSE
  it('phone validation should be false', () => {
    expect(service.validatePhone("1234567")).toBe(false);
  })

  //validatePhone TRUE
  it('phone validation should be true', () => {
    expect(service.validatePhone("+39 3384283440")).toBe(true);
  })

  //validatePassword FALSE
  it('password validation should be false', () => {
    expect(service.validatePassword(" ")).toBe(false);
  })

  //validatePassword TRUE
  it('password validation should be true', () => {
    expect(service.validatePassword("Bi8cAdJv0B")).toBe(true);
  })






});
