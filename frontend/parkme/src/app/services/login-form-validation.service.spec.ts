import { TestBed } from '@angular/core/testing';

import { LoginFormValidationService } from './login-form-validation.service';

describe('LoginFormValidationService', () => {
  let service: LoginFormValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoginFormValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
