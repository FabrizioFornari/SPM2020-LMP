import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VigilantPanelComponent } from './vigilant-panel.component';

describe('VigilantPanelComponent', () => {
  let component: VigilantPanelComponent;
  let fixture: ComponentFixture<VigilantPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VigilantPanelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VigilantPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
