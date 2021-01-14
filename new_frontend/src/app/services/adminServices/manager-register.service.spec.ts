import { TestBed } from '@angular/core/testing';

import { ManagerRegisterService } from './manager-register.service';

describe('ManagerRegisterService', () => {
  let service: ManagerRegisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManagerRegisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
