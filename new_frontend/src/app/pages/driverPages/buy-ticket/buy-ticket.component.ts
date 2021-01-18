import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { Router } from "@angular/router";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { PayTicketComponent } from "src/app/modals/driverModal/pay-ticket/pay-ticket.component";
import { SelectBookingTypeComponent } from "src/app/modals/driverModal/select-booking-type/select-booking-type.component";
import { NgxToastService } from "src/app/services/commonServices/ngx-toast.service";
import { TicketService } from "src/app/services/driverServices/ticket.service";

const GOOGLE_MAPS = "https://www.google.com/maps/dir//";

@Component({
  selector: "app-buy-ticket",
  templateUrl: "./buy-ticket.component.html",
  styleUrls: ["./buy-ticket.component.css"],
})
export class BuyTicketComponent implements OnInit {
  isCurrentBooking: boolean = false;

  isCurrentTicket: boolean = false;

  isCurrentSubscription: boolean = false;

  currentBooking;

  currentTicket;

  ticketHistory = [];

  constructor(
    private titleService: Title,
    private modalService: NgbModal,
    private toast: NgxToastService,
    private ticket: TicketService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle("ParkMe | Buy Ticket");
    this.getCurrentBooking();
    this.getTicketHistory();
  }

  newBooking() {
    const modalRef = this.modalService.open(SelectBookingTypeComponent);
    modalRef.result.then(
      () => {
        console.log("Modal Select Booking Type Closed");
      },
      () => {
        console.log("Modal Select Booking Type  Dismissed");
      }
    );
  }

  newSubscription() {
    this.router.navigateByUrl("/map", { state: { subscription: true } });
  }

  showHistory() {
    this.router.navigate(["driver/ticket-history"]);
  }

  getCurrentBooking() {
    this.ticket.driverGetCurrentBooking().subscribe(
      (success) => {
        this.currentBooking = success;
        this.currentBooking.timestamp = `${new Date(
          this.currentBooking.timestamp
        ).toLocaleDateString("it-IT")} (${new Date(
          this.currentBooking.timestamp
        ).toLocaleTimeString("it-IT")})`;
        this.isCurrentBooking = true;
      },
      (error) => {
        console.log(error);
        this.isCurrentBooking = false;
      }
    );
  }

  getTicketHistory() {
    this.ticketHistory = [];
    this.ticket.driverGetTicketHistory().subscribe(
      (success) => {
        console.log(success);
        success.forEach((element) => {
          let readable_date = `${new Date(
            element.expiringTimestamp
          ).toLocaleDateString("it-IT")} (${new Date(
            element.expiringTimestamp
          ).toLocaleTimeString("it-IT")})`;
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
  }

  confirm(curBook) {
    let modalRef = this.modalService.open(PayTicketComponent);
    modalRef.componentInstance.CURRENT_BOOKING = curBook;
    modalRef.result.then(
      () => {
        console.log("Modal Confirm Parking Lot Closed");
        this.ngOnInit();
      },
      () => {
        console.log("Modal Confirm Parking Lot Dismissed");
        this.ngOnInit();
      }
    );
  }

  maps(lat: any, long: any) {
    window.open(GOOGLE_MAPS + `${lat},${long}`);
  }

  cancel() {
    this.ticket.driverCancelBooking().subscribe(
      (success) => {
        console.log(success);
        this.isCurrentBooking = false;
        this.toast.createToster("success", "Successfully Cancelled");
        this.ngOnInit();
      },
      (error) => {
        console.log(error);
        this.toast.createToster("error", "Error while deleting Booking");
      }
    );
  }
}
