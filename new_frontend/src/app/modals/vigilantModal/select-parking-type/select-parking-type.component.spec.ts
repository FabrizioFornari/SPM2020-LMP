import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectParkingTypeComponent } from './select-parking-type.component';

describe('SelectParkingTypeComponent', () => {
  let component: SelectParkingTypeComponent;
  let fixture: ComponentFixture<SelectParkingTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelectParkingTypeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectParkingTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
