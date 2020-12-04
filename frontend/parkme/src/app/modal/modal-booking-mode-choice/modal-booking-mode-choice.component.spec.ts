import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalBookingModeChoiceComponent } from './modal-booking-mode-choice.component';

describe('ModalBookingModeChoiceComponent', () => {
  let component: ModalBookingModeChoiceComponent;
  let fixture: ComponentFixture<ModalBookingModeChoiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalBookingModeChoiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalBookingModeChoiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
