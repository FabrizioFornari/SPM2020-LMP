import { TestBed } from '@angular/core/testing';

import { ParkingManagerVigilantRegistrationService } from './parking-manager-vigilant-registration.service';

describe('ParkingManagerVigilantRegistrationService', () => {
  let service: ParkingManagerVigilantRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParkingManagerVigilantRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
