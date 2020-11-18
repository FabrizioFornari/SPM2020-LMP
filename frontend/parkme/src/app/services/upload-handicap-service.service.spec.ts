import { TestBed } from '@angular/core/testing';

import { UploadHandicapServiceService } from './upload-handicap-service.service';

describe('UploadHandicapServiceService', () => {
  let service: UploadHandicapServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UploadHandicapServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
