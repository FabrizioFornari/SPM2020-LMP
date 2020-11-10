import { TestBed } from '@angular/core/testing';

import { UnifiedLoginService } from './unified-login.service';

describe('UnifiedLoginService', () => {
  let service: UnifiedLoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UnifiedLoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
