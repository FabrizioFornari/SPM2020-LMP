import { TestBed } from '@angular/core/testing';

import { DriverRegistrationValidationService } from './driver-registration-validation.service';

describe('DriverRegistrationValidationService', () => {
  let service: DriverRegistrationValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriverRegistrationValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
