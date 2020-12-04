import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalBookingModeChoiceComponent } from 'src/app/modal/modal-booking-mode-choice/modal-booking-mode-choice.component';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
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


  ticketClick(){
    alert("Row Clicked");
  }

  confirm(){
    alert("Confirmed");
  }

  maps(){
    alert("Maps");
  }

  cancel(){
    alert("Cancelled");
  }

}
