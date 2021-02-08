import { Component, Input, OnInit } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";
import { NgxToastService } from "src/app/services/commonServices/ngx-toast.service";
import { ParkingLotService } from "src/app/services/vigilantServices/parking-lot.service";

@Component({
  selector: "app-park-info",
  templateUrl: "./park-info.component.html",
  styleUrls: ["./park-info.component.css"],
})
export class ParkInfoComponent implements OnInit {
  @Input() INFO: any;

  state: string = "";

  constructor(
    public activeModal: NgbActiveModal,
    private parkingService: ParkingLotService,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {
    console.table(this.INFO);
    this.state = this.INFO.status || this.INFO.personalParkingLotStatus;
  }

  disablePark(body) {
    console.log(body);
    this.parkingService.vigilantSetParkDisabled(body).subscribe(
      () => {
        this.toast.createToster("success", "Successfully Disabled");

        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toast.createToster("error", "Error Disabling");

        this.activeModal.dismiss();
      }
    );
  }

  enablePark(body) {
    console.log(body);
    this.parkingService.vigilantSetParkEnabled(body).subscribe(
      (success) => {
        console.log(success);
        this.toast.createToster("success", "Successfully Enabled");

        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toast.createToster("error", "Error Enabling");

        this.activeModal.dismiss();
      }
    );
  }

  disablePersonalPark(body) {
    console.log(body);
    this.parkingService.vigilantSetPersonalParkDisabled(body).subscribe(
      (success) => {
        console.log(success);
        this.toast.createToster("success", "Successfully Disabled");

        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toast.createToster("error", "Error Disabling");

        this.activeModal.dismiss();
      }
    );
  }

  enablePersonalPark(body) {
    console.log(body);
    this.parkingService.vigilantSetPersonalParkEnabled(body).subscribe(
      (success) => {
        console.log(success);
        this.toast.createToster("success", "Successfully Enabled");

        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toast.createToster("error", "Error Enabling");

        this.activeModal.dismiss();
      }
    );
  }
}
