import { TestBed } from '@angular/core/testing';

import { AccountManagementValidatorService } from './account-management-validator.service';

describe('AccountManagementValidatorService', () => {
  let service: AccountManagementValidatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccountManagementValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
