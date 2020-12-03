import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';
import { ToastrService } from 'ngx-toastr';

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
    private parkingService: ParkingLotServiceService,
    private toastrService: ToastrService,
  ) {}

  ngOnInit(): void {}

  bookAPark(parking: any) {
    this.parkingService.driverBookParkingLot(parking).subscribe(
      (success) => {
        console.log(success);
        this.activeModal.close();
        window.open(GOOGLE_MAPS + `${parking.coordinates.latitude},${parking.coordinates.longitude}`);
      },
      (error) => {
        console.log(error);
        if (error.status == 409) {
          this.toastrService.warning('You have already book a spot!');
        }
        this.activeModal.dismiss();
      }
    );
  }

  backButton(){
    this.activeModal.dismiss();
  }
}
