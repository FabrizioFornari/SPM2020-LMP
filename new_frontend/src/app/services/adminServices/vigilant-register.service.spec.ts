import { TestBed } from '@angular/core/testing';

import { VigilantRegisterService } from './vigilant-register.service';

describe('VigilantRegisterService', () => {
  let service: VigilantRegisterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VigilantRegisterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
