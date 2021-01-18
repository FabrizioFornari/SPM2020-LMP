import { TestBed } from '@angular/core/testing';

import { IsManagerService } from './is-manager.service';

describe('IsManagerService', () => {
  let service: IsManagerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IsManagerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
