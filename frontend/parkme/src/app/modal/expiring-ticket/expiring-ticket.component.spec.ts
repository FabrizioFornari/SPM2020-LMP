import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpiringTicketComponent } from './expiring-ticket.component';

describe('ExpiringTicketComponent', () => {
  let component: ExpiringTicketComponent;
  let fixture: ComponentFixture<ExpiringTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExpiringTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExpiringTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
