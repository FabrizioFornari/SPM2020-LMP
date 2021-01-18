import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddParkingLotComponent } from './add-parking-lot.component';

describe('AddParkingLotComponent', () => {
  let component: AddParkingLotComponent;
  let fixture: ComponentFixture<AddParkingLotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddParkingLotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
