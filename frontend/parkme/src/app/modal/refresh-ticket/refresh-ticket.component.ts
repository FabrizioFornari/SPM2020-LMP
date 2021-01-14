import { formatCurrency } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { info } from 'console';
import { ToastrService } from 'ngx-toastr';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

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
    private parkingService: ParkingLotServiceService,
    private toastrService: ToastrService
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

    this.parkingService.driverRefreshTicket(body).subscribe(
      (success) => {
        console.log(success);
        this.toastrService.success('Ticket Refreshed');
        this.hour = null;
        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toastrService.warning('Error Refreshing Ticket');
        this.hour = null;
        this.activeModal.dismiss();
      }
    );
  }
}
