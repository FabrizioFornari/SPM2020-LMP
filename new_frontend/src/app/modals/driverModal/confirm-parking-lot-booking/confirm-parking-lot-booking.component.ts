import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { TicketService } from 'src/app/services/driverServices/ticket.service';

@Component({
  selector: 'app-confirm-parking-lot',
  templateUrl: './confirm-parking-lot-booking.component.html',
  styleUrls: ['./confirm-parking-lot-booking.component.css'],
})
export class ConfirmParkingLotBookingComponent implements OnInit {
  @Input() PARKINGLOT: any;

  userInfo;

  constructor(
    public activeModal: NgbActiveModal,
    private ticketService: TicketService,
    private toast: NgxToastService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userInfo = JSON.parse(localStorage.getItem('user'));
  }

  bookAPark(parking: any) {
    this.ticketService.driverBookParkingLot(parking).subscribe(
      (success) => {
        this.toast.createToster('success', success.message);
        this.activeModal.close();
        this.router.navigate(['/buy-ticket']);
      },
      (error) => {
        console.log(error);
        if (error.status == 400) {
          this.toast.createToster('error', 'Bad Request');
        } else if (error.status == 401) {
          this.toast.createToster('error', 'Unauthorized');
        } else if (error.status == 403) {
          this.toast.createToster('error', 'Forbidden');
        } else if (error.status == 404) {
          this.toast.createToster('error', 'Not Found');
        } else if (error.status == 409) {
          this.toast.createToster('error', 'Conflict');
        } else if (error.status == 500) {
          this.toast.createToster('error', 'Server Error');
        } else if (error.status == 503) {
          this.toast.createToster('error', 'Server Unavailable');
        } else this.toast.createToster('error', 'Unknown Error');
        this.activeModal.dismiss();
      }
    );
  }

  backButton() {
    this.activeModal.dismiss();
  }
}
