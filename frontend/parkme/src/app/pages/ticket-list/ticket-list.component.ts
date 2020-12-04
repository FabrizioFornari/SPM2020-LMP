import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalBookingModeChoiceComponent } from 'src/app/modal/modal-booking-mode-choice/modal-booking-mode-choice.component';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

const GOOGLE_MAPS = 'https://www.google.com/maps/dir//';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css'],
})
export class TicketListComponent implements OnInit {
  isCurrentBooking: boolean = false;

  currentBooking;

  constructor(
    private modalService: NgbModal,
    private parkingService: ParkingLotServiceService
  ) {}

  ngOnInit(): void {
    this.getCurrentBooking();
  }

  newBooking() {
    const modalRef = this.modalService.open(ModalBookingModeChoiceComponent);
    modalRef.result.then(
      () => {
        console.log('Modal  Closed');
      },
      () => {
        console.log('Modal  Dismissed');
      }
    );
  }

  getCurrentBooking() {
    this.parkingService.driverGetCurrentBooking().subscribe(
      (success) => {
        console.log(success);
        this.currentBooking = success;
        this.currentBooking.timestamp = `${new Date(
          this.currentBooking.timestamp
        ).toLocaleDateString('it-IT')} (${new Date(
          this.currentBooking.timestamp
        ).toLocaleTimeString('it-IT')})`;
        this.isCurrentBooking = true;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  ticketClick() {
    alert('Row Clicked');
  }

  confirm() {
    alert('Confirmed');
  }

  maps(lat: any, long: any) {
    window.open(GOOGLE_MAPS + `${lat},${long}`);
  }

  cancel() {
    alert('Cancelled');
  }
}
