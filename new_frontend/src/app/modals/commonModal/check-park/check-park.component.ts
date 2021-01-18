import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { ParkingLotService } from 'src/app/services/vigilantServices/parking-lot.service';
import { RefreshTicketComponent } from '../../driverModal/refresh-ticket/refresh-ticket.component';
import { ParkInfoComponent } from '../../vigilantModal/park-info/park-info.component';

@Component({
  selector: 'app-check-park',
  templateUrl: './check-park.component.html',
  styleUrls: ['./check-park.component.css'],
})
export class CheckParkComponent implements OnInit {
  @Input() notification: any;

  constructor(
    public activeModal: NgbActiveModal,
    private modalService: NgbModal,
    private parkingService: ParkingLotService,
    private toast: NgxToastService
  ) {}

  ROLE: string = '';

  ngOnInit(): void {
    console.log(this.notification);
    this.ROLE = JSON.parse(localStorage.getItem('user')).roles;
  }

  openModalVigilantCheckInfo(info) {
    this.parkingService
      .vigilantGetParkInfo(info.street, info.numberOfParkingLot)
      .subscribe(
        (success) => {
          const modalRef = this.modalService.open(ParkInfoComponent);
          modalRef.componentInstance.INFO = success;
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
        },
        (error) => {
          console.log(error);
          this.toast.createToster('error', 'Error Getting Park Info');
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
