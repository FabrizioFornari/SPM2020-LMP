import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VigilantCheckParkComponent } from './vigilant-check-park.component';

describe('VigilantCheckParkComponent', () => {
  let component: VigilantCheckParkComponent;
  let fixture: ComponentFixture<VigilantCheckParkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VigilantCheckParkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VigilantCheckParkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
