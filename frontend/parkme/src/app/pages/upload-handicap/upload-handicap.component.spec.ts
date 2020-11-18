import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadHandicapComponent } from './upload-handicap.component';

describe('UploadHandicapComponent', () => {
  let component: UploadHandicapComponent;
  let fixture: ComponentFixture<UploadHandicapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadHandicapComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadHandicapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
