import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPersonalParkingLotComponent } from './add-personal-parking-lot.component';

describe('AddPersonalParkingLotComponent', () => {
  let component: AddPersonalParkingLotComponent;
  let fixture: ComponentFixture<AddPersonalParkingLotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPersonalParkingLotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPersonalParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
