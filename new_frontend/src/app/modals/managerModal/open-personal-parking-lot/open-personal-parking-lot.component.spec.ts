import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenPersonalParkingLotComponent } from './open-personal-parking-lot.component';

describe('OpenPersonalParkingLotComponent', () => {
  let component: OpenPersonalParkingLotComponent;
  let fixture: ComponentFixture<OpenPersonalParkingLotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OpenPersonalParkingLotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenPersonalParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
