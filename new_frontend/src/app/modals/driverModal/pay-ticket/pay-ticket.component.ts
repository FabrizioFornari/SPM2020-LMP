import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { TicketService } from 'src/app/services/driverServices/ticket.service';

@Component({
  selector: 'app-pay-ticket',
  templateUrl: './pay-ticket.component.html',
  styleUrls: ['./pay-ticket.component.css'],
})
export class PayTicketComponent implements OnInit {
  hours: Array<String> = ['0.005', '1', '2', '3', '4', '12'];
  hour: string = null;

  @Input() CURRENT_BOOKING: any;

  constructor(
    public activeModal: NgbActiveModal,
    private ticket: TicketService,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {}

  backButton() {
    this.hour = null;
    this.activeModal.dismiss();
  }

  BUY(form: NgForm) {
    console.log(Date.now());

    let body = {
      street: this.CURRENT_BOOKING.street,
      numberOfParkingLot: this.CURRENT_BOOKING.numberOfParkingLot,
      username: this.CURRENT_BOOKING.username,
      moneySpent: form.value.hour * this.CURRENT_BOOKING.pricePerHour,
      expiringTimestamp: Date.now() + form.value.hour * 3600000,
    };
    this.ticket.driverBuyTicket(body).subscribe(
      (success) => {
        console.log(success);
        this.toast.createToster('success', 'Ticket Bought');
        this.hour = null;
        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toast.createToster('error', 'Error Buying Ticket');
        this.hour = null;
        this.activeModal.dismiss();
      }
    );
  }
}
