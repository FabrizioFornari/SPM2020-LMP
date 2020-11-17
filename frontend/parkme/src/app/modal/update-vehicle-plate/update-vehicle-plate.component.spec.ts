import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateVehiclePlateComponent } from './update-vehicle-plate.component';

describe('UpdateVehiclePlateComponent', () => {
  let component: UpdateVehiclePlateComponent;
  let fixture: ComponentFixture<UpdateVehiclePlateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateVehiclePlateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateVehiclePlateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
