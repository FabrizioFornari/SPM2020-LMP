import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoordinatesModalComponent } from './coordinates-modal.component';

describe('CoordinatesModalComponent', () => {
  let component: CoordinatesModalComponent;
  let fixture: ComponentFixture<CoordinatesModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoordinatesModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoordinatesModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
