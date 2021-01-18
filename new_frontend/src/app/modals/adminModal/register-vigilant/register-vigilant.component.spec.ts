import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterVigilantComponent } from './register-vigilant.component';

describe('RegisterVigilantComponent', () => {
  let component: RegisterVigilantComponent;
  let fixture: ComponentFixture<RegisterVigilantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterVigilantComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterVigilantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
