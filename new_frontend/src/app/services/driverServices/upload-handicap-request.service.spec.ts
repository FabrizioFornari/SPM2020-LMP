import { TestBed } from '@angular/core/testing';

import { UploadHandicapRequestService } from './upload-handicap-request.service';

describe('UploadHandicapRequestService', () => {
  let service: UploadHandicapRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UploadHandicapRequestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
