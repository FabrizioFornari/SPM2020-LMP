import { TestBed } from '@angular/core/testing';

import { ParkingLotServiceService } from './parking-lot-service.service';

describe('ParkingLotServiceService', () => {
  let service: ParkingLotServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParkingLotServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
