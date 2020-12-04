import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

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
    private router: Router
  ) {}

  ngOnInit(): void {}

  bookAPark(parking: any) {
    this.parkingService.driverBookParkingLot(parking).subscribe(
      (success) => {
        this.toastrService.success(success.message);
        this.activeModal.close();
        this.router.navigate(['/ticket-list']);
      },
      (error) => {
        console.log(error);
        if (error.status == 409) {
          this.toastrService.warning(error.error);
        } else {
          this.toastrService.warning('Unknown Error');
        }
        this.activeModal.dismiss();
      }
    );
  }

  backButton() {
    this.activeModal.dismiss();
  }
}
