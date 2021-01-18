import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { TicketService } from 'src/app/services/driverServices/ticket.service';

@Component({
  selector: 'app-refresh-ticket',
  templateUrl: './refresh-ticket.component.html',
  styleUrls: ['./refresh-ticket.component.css'],
})
export class RefreshTicketComponent implements OnInit {
  hours: Array<String> = ['1', '2', '3', '4', '12'];
  hour: string = null;

  @Input() INFO: any;

  constructor(
    public activeModal: NgbActiveModal,
    private ticketService: TicketService,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {}

  backButton() {
    this.hour = null;
    this.activeModal.dismiss();
  }

  refresh(form) {
    let body = {
      street: this.INFO.street,
      numberOfParkingLot: this.INFO.numberOfParkingLot,
      extraHours: parseInt(form.value.hour),
    };

    console.log(body);

    this.ticketService.driverRefreshTicket(body).subscribe(
      (success) => {
        console.log(success);
        this.toast.createToster('success', 'Ticket Refreshed');
        this.hour = null;
        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toast.createToster('error', 'Error Refreshing Ticket');
        this.hour = null;
        this.activeModal.dismiss();
      }
    );
  }
}
