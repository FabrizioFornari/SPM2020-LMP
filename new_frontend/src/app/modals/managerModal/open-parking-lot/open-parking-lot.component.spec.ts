import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenParkingLotComponent } from './open-parking-lot.component';

describe('OpenParkingLotComponent', () => {
  let component: OpenParkingLotComponent;
  let fixture: ComponentFixture<OpenParkingLotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OpenParkingLotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
