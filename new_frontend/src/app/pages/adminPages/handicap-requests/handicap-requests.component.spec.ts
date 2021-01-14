import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HandicapRequestsComponent } from './handicap-requests.component';

describe('HandicapRequestsComponent', () => {
  let component: HandicapRequestsComponent;
  let fixture: ComponentFixture<HandicapRequestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HandicapRequestsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HandicapRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
