import { TestBed } from '@angular/core/testing';

import { IsVigilantService } from './is-vigilant.service';

describe('IsVigilantService', () => {
  let service: IsVigilantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IsVigilantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
