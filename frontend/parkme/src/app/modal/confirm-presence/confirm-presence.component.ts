import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';
import { ConfirmParkingLotComponent } from '../confirm-parking-lot/confirm-parking-lot.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
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
    private toastrService: ToastrService,
    private parkingService: ParkingLotServiceService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {}

  assert(){
    this.toastrService.warning('Please Confirm Your Arrival');
    this.router.navigate(['/ticket-list']);
    this.activeModal.close();
  }

  deny(){
    this.parkingService.driverChangeParkingLot().subscribe(
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
    let modalRef = this.modalService.open(ConfirmParkingLotComponent);
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
