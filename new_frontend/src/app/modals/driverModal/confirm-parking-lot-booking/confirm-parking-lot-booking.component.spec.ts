import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmParkingLotBookingComponent } from './confirm-parking-lot-booking.component';

describe('ConfirmParkingLotBookingComponent', () => {
  let component: ConfirmParkingLotBookingComponent;
  let fixture: ComponentFixture<ConfirmParkingLotBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmParkingLotBookingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmParkingLotBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
