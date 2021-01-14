import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HandicapRequestComponent } from './handicap-request.component';

describe('HandicapRequestComponent', () => {
  let component: HandicapRequestComponent;
  let fixture: ComponentFixture<HandicapRequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HandicapRequestComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HandicapRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
