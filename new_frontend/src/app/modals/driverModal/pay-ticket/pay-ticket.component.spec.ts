import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayTicketComponent } from './pay-ticket.component';

describe('PayTicketComponent', () => {
  let component: PayTicketComponent;
  let fixture: ComponentFixture<PayTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PayTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
