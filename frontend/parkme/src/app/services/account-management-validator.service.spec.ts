import { TestBed } from '@angular/core/testing';

import { AccountManagementValidatorService } from './account-management-validator.service';

describe('AccountManagementValidatorService', () => {
  let service: AccountManagementValidatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountManagementValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


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

  //validatePlate FALSE
  it('plate validation should be false', () => {
    expect(service.validatePlate("1234567")).toBe(false);
  })

  //validatePlate TRUE
  it('plate validation should be true', () => {
    expect(service.validatePlate("ES943VB")).toBe(true);
  })

  //validateVehicle FALSE
  it('vehicle validation should be false', () => {
    expect(service.validateVehicle(" ")).toBe(false);
  })

  //validateVehicle TRUE
  it('vehicle validation should be true', () => {
    expect(service.validateVehicle("2 Wheels Vehicle")).toBe(true);
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
