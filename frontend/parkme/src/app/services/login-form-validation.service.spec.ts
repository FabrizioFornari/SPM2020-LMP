import { TestBed } from '@angular/core/testing';

import { LoginFormValidationService } from './login-form-validation.service';

describe('LoginFormValidationService', () => {
  let service: LoginFormValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginFormValidationService);
  });

  //validateEmail FALSE
  it('email validation should be false', () => {
    expect(service.validateEmail('mario@rossi')).toBe(false);
  });

  //validateEmail TRUE
  it('email validation should be true', () => {
    expect(service.validateEmail('mario@rossi.it')).toBe(true);
  });

  //validatePassword FALSE
  it('password validation should be false', () => {
    expect(service.validatePassword(' ')).toBe(false);
  });

  //validatePassword TRUE
  it('password validation should be true', () => {
    expect(service.validatePassword('Bi8cAdJv0B')).toBe(true);
  });
});
