import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenHandicapRequestComponent } from './open-handicap-request.component';

describe('OpenHandicapRequestComponent', () => {
  let component: OpenHandicapRequestComponent;
  let fixture: ComponentFixture<OpenHandicapRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OpenHandicapRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenHandicapRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
