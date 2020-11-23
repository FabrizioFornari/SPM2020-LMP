import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertParkingLotComponent } from './insert-parking-lot.component';

describe('InsertParkingLotComponent', () => {
  let component: InsertParkingLotComponent;
  let fixture: ComponentFixture<InsertParkingLotComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsertParkingLotComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InsertParkingLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
