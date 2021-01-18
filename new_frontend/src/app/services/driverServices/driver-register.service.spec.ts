import { TestBed } from '@angular/core/testing';

import { DriverRegisterService } from './driver-register.service';

describe('DriverRegisterService', () => {
  let service: DriverRegisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriverRegisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
