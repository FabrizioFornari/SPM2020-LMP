import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

@Component({
  selector: 'app-park-info',
  templateUrl: './park-info.component.html',
  styleUrls: ['./park-info.component.css'],
})
export class ParkInfoComponent implements OnInit {
  @Input() INFO: any;

  constructor(
    public activeModal: NgbActiveModal,
    private parkingService: ParkingLotServiceService,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {
    console.table(this.INFO);
  }

  disablePark(body) {
    console.log(body);
    this.parkingService.vigilantSetParkDisabled(body).subscribe(
      (success) => {
        console.log(success);
        this.toastrService.success('Successfully Disabled');

        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toastrService.warning('Error During Operation');

        this.activeModal.dismiss();
      }
    );
  }

  enablePark(body) {
    console.log(body);
    this.parkingService.vigilantSetParkEnabled(body).subscribe(
      (success) => {
        console.log(success);
        this.toastrService.success('Successfully Enabled');

        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toastrService.warning('Error During Operation');

        this.activeModal.dismiss();
      }
    );
  }
}
