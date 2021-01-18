import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectBookingTypeComponent } from './select-booking-type.component';

describe('SelectBookingTypeComponent', () => {
  let component: SelectBookingTypeComponent;
  let fixture: ComponentFixture<SelectBookingTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectBookingTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectBookingTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
