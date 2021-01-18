import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckParkComponent } from './check-park.component';

describe('CheckParkComponent', () => {
  let component: CheckParkComponent;
  let fixture: ComponentFixture<CheckParkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckParkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckParkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
