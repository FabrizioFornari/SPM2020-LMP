import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';


const GOOGLE_MAPS = "https://www.google.com/maps/dir//";


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
        window.open(GOOGLE_MAPS + `${parking.coordinates.latitude},${parking.coordinates.longitude}`);
      },
      (error) => {
        console.log(error);
        this.activeModal.dismiss();
      }
    );
  }
}
