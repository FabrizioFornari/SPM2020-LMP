import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.css'],
})
export class BuyTicketComponent implements OnInit {
  hours: Array<String> = ['1', '2', '3', '4', '12'];
  hour: string = null;

  @Input() CURRENT_BOOKING: any;

  constructor(
    public activeModal: NgbActiveModal,
    private parkingService: ParkingLotServiceService,
    private toastrService: ToastrService,
    private router: Router
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
    this.parkingService.driverBuyTicket(body).subscribe(
      (success) => {
        console.log(success);
        this.toastrService.success('Ticket Bought');
        this.hour = null;
        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toastrService.warning('Error Buying Ticket');
        this.hour = null;
        this.activeModal.dismiss();
      }
    );
  }
}
