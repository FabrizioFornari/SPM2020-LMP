import { TestBed } from '@angular/core/testing';

import { ParkingManagerVigilantRegistrationValidationService } from './parking-manager-vigilant-registration-validation.service';

describe('ParkingManagerVigilantRegistrationValidationService', () => {
  let service: ParkingManagerVigilantRegistrationValidationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParkingManagerVigilantRegistrationValidationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
