import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TicketService } from 'src/app/services/driverServices/ticket.service';
import { ConfirmParkingLotBookingComponent } from '../confirm-parking-lot-booking/confirm-parking-lot-booking.component';

@Component({
  selector: 'app-coordinates-modal',
  templateUrl: './coordinates-modal.component.html',
  styleUrls: ['./coordinates-modal.component.css'],
})
export class CoordinatesModalComponent implements OnInit {
  @Input() LAT: number;
  @Input() LNG: number;

  constructor(
    public activeModal: NgbActiveModal,
    private ticketService: TicketService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {}

  backButton() {
    this.activeModal.dismiss();
  }

  confirm(lat: number, lng: number) {
    this.ticketService.driverGetNearestParkingLot(lat, lng).subscribe(
      (success) => {
        this.activeModal.dismiss();
        this.openModalBookParking(success);
      },
      (error) => {
        console.log(error);
        this.activeModal.dismiss();
      }
    );
  }

  openModalBookParking(parkingLot: any) {
    let modalRef = this.modalService.open(ConfirmParkingLotBookingComponent);
    modalRef.componentInstance.PARKINGLOT = parkingLot;
    modalRef.result.then(
      () => {
        console.log('Modal Confirm Parking Lot Closed');
        this.ngOnInit();
      },
      () => {
        console.log('Modal Confirm Parking Lot Dismissed');
      }
    );
  }
}
