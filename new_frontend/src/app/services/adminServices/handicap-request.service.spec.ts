import { TestBed } from '@angular/core/testing';

import { HandicapRequestService } from './handicap-request.service';

describe('HandicapRequestService', () => {
  let service: HandicapRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HandicapRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
