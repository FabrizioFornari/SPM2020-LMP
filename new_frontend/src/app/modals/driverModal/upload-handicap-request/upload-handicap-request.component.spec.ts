import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadHandicapRequestComponent } from './upload-handicap-request.component';

describe('UploadHandicapRequestComponent', () => {
  let component: UploadHandicapRequestComponent;
  let fixture: ComponentFixture<UploadHandicapRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadHandicapRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadHandicapRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
