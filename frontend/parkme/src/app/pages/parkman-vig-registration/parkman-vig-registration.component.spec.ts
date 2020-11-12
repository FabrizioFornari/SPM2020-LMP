import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParkmanVigRegistrationComponent } from './parkman-vig-registration.component';

describe('ParkmanVigRegistrationComponent', () => {
  let component: ParkmanVigRegistrationComponent;
  let fixture: ComponentFixture<ParkmanVigRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParkmanVigRegistrationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ParkmanVigRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
