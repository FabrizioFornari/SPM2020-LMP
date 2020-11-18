import { TestBed } from '@angular/core/testing';

import { HandicapRequestDownloadService } from './handicap-request-download.service';

describe('HandicapRequestDownloadService', () => {
  let service: HandicapRequestDownloadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HandicapRequestDownloadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
