import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { BuyTicketComponent } from 'src/app/modal/buy-ticket/buy-ticket.component';
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

  isCurrentTicket: boolean = false;

  currentBooking;

  currentTicket;

  ticketHistory = [];

  constructor(
    private modalService: NgbModal,
    private parkingService: ParkingLotServiceService,
    private toastrService: ToastrService,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Ticket List');
    this.getCurrentBooking();
    this.getTicketHistory();
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
        this.isCurrentBooking = false;
      }
    );
  }

  confirm(curBook) {
    let modalRef = this.modalService.open(BuyTicketComponent);
    modalRef.componentInstance.CURRENT_BOOKING = curBook;
    modalRef.result.then(
      () => {
        console.log('Modal Confirm Parking Lot Closed');
        this.ngOnInit();
      },
      () => {
        console.log('Modal Confirm Parking Lot Dismissed');
        this.ngOnInit();
      }
    );
  }

  maps(lat: any, long: any) {
    window.open(GOOGLE_MAPS + `${lat},${long}`);
  }

  cancel() {
    this.parkingService.driverCancelBooking().subscribe(
      (success) => {
        console.log(success);
        this.isCurrentBooking = false;
        this.toastrService.success('Successfully Deleted');
        this.ngOnInit();
      },
      (error) => {
        console.log(error);
        this.toastrService.warning('Error while deleting Booking');
      }
    );
  }

  getTicketHistory() {
    this.ticketHistory = [];
    this.parkingService.driverGetTicketHistory().subscribe(
      (success) => {
        console.log(success);
        success.forEach((element) => {
          let readable_date = `${new Date(
            element.expiringTimestamp
          ).toLocaleDateString('it-IT')} (${new Date(
            element.expiringTimestamp
          ).toLocaleTimeString('it-IT')})`;
          if (element.expiringTimestamp > Date.now()) {
            this.currentTicket = element;
            this.currentTicket.expiringTimestamp = readable_date;
            this.isCurrentTicket = true;
          } else {
            element.expiringTimestamp = readable_date;
            this.ticketHistory.push(element);
          }
        });
      },
      (error) => {
        console.log(error);
        this.ticketHistory = [];
      }
    );
    console.log(this.ticketHistory);
  }
}
