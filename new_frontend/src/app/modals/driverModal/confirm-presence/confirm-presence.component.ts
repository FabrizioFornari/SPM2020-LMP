import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { TicketService } from 'src/app/services/driverServices/ticket.service';
import { ConfirmParkingLotBookingComponent } from '../confirm-parking-lot-booking/confirm-parking-lot-booking.component';
@Component({
  selector: 'app-confirm-presence',
  templateUrl: './confirm-presence.component.html',
  styleUrls: ['./confirm-presence.component.css'],
})
export class ConfirmPresenceComponent implements OnInit {
  @Input() notification: any;

  constructor(
    public activeModal: NgbActiveModal,
    private router: Router,
    private toast: NgxToastService,
    private ticketService: TicketService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {}

  assert() {
    this.toast.createToster('error', 'Please Confirm Your Arrival');
    this.router.navigate(['/buy-ticket']);
    this.activeModal.close();
  }

  deny() {
    this.ticketService.driverChangeParkingLot().subscribe(
      (success) => {
        this.openModalBookParking(success);
      },
      (error) => {
        console.log(error);
      }
    );
    this.activeModal.close();
  }

  openModalBookParking(parkingLot: any) {
    let modalRef = this.modalService.open(ConfirmParkingLotBookingComponent);
    modalRef.componentInstance.PARKINGLOT = parkingLot;
    modalRef.result.then(
      () => {
        console.log('Modal Confirm Parking Lot Closed');
        this.router.navigate(['/buy-ticket']);
        window.location.reload();
      },
      () => {
        console.log('Modal Confirm Parking Lot Dismissed');
      }
    );
  }
}
