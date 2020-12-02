import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

@Component({
  selector: 'app-confirm-parking-lot',
  templateUrl: './confirm-parking-lot.component.html',
  styleUrls: ['./confirm-parking-lot.component.css'],
})
export class ConfirmParkingLotComponent implements OnInit {
  @Input() PARKINGLOT: any;

  constructor(
    public activeModal: NgbActiveModal,
    private parkingService: ParkingLotServiceService
  ) {}

  ngOnInit(): void {}

  bookAPark(parking: any) {
    this.parkingService.driverBookParkingLot(parking).subscribe(
      (success) => {
        console.log(success);
        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.activeModal.dismiss();
      }
    );
  }
}
