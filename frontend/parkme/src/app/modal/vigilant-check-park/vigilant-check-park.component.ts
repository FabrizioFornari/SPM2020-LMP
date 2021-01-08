import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ParkInfoComponent } from '../park-info/park-info.component';
import { RefreshTicketComponent } from '../refresh-ticket/refresh-ticket.component';

@Component({
  selector: 'app-vigilant-check-park',
  templateUrl: './vigilant-check-park.component.html',
  styleUrls: ['./vigilant-check-park.component.css'],
})
export class VigilantCheckParkComponent implements OnInit {
  @Input() notification: any;

  constructor(
    public activeModal: NgbActiveModal,
    private modalService: NgbModal
  ) {}

  ROLE: string = '';

  ngOnInit(): void {
    console.log(this.notification);
    this.ROLE = JSON.parse(localStorage.getItem('user')).roles;
  }

  openModalVigilantCheckInfo(info) {
    const modalRef = this.modalService.open(ParkInfoComponent);
    modalRef.componentInstance.INFO = info;
    modalRef.result.then(
      () => {
        console.log('Modal Request Closed');
        this.activeModal.close();
      },
      () => {
        console.log('Modal Request Closed');
        this.activeModal.close();
      }
    );
  }

  openModalRefreshTicket(info) {
    const modalRef = this.modalService.open(RefreshTicketComponent);
    modalRef.componentInstance.INFO = info.parkingLot;
    modalRef.result.then(
      () => {
        console.log('Modal Request Closed');
        this.activeModal.close();
      },
      () => {
        console.log('Modal Request Closed');
        this.activeModal.close();
      }
    );
  }
}
