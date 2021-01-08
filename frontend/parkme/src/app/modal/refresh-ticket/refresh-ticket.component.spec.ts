import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RefreshTicketComponent } from './refresh-ticket.component';

describe('RefreshTicketComponent', () => {
  let component: RefreshTicketComponent;
  let fixture: ComponentFixture<RefreshTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RefreshTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RefreshTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
