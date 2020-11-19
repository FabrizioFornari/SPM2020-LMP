import { TestBed } from '@angular/core/testing';

import { HandleHandicapRequestsService } from './handle-handicap-requests.service';

describe('HandleHandicapRequestsService', () => {
  let service: HandleHandicapRequestsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HandleHandicapRequestsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
