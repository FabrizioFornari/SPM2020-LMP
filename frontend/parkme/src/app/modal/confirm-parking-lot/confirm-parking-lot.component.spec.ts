import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmParkingLotComponent } from './confirm-parking-lot.component';

describe('ConfirmParkingLotComponent', () => {
  let component: ConfirmParkingLotComponent;
  let fixture: ComponentFixture<ConfirmParkingLotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmParkingLotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
