import { Component, Input, OnInit } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";
import { NgxToastService } from "src/app/services/commonServices/ngx-toast.service";
import { ParkingLotServiceService } from "src/app/services/managerServices/parking-lot-service.service";

@Component({
  selector: "app-open-personal-parking-lot",
  templateUrl: "./open-personal-parking-lot.component.html",
  styleUrls: ["./open-personal-parking-lot.component.css"],
})
export class OpenPersonalParkingLotComponent implements OnInit {
  @Input() PARK: any;

  isLoading: boolean = false;

  handicapType: Array<String> = ["True", "False"];
  vehicleType: Array<String> = [
    "2 Wheels Vehicle",
    "4 Wheels Standard Vehicle",
    "4 Wheels Big Vehicle",
  ];

  newStreet: string = "";
  newNumberOfParkingLot: number;
  newIsHandicapParkingLot: string = "";
  newPrice: number;
  newTypeOfVehicle: string = "";
  newLatitude: string = "";
  newLongitude: string = "";

  constructor(
    public activeModal: NgbActiveModal,
    private toast: NgxToastService,
    private parkingLotService: ParkingLotServiceService
  ) {}

  ngOnInit(): void {}

  deleteParkingLot(park: { street: string; numberOfParkingLot: number }) {
    const body = {
      street: park.street,
      numberOfParkingLot: park.numberOfParkingLot,
    };
    this.parkingLotService.deletePersonalParkingLot(body).subscribe(
      () => {
        this.toast.createToster("success", "Personal Parking Lot Deleted");
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 400) {
          this.toast.createToster("error", "Bad Request");
        } else if (error.status == 401) {
          this.toast.createToster("error", "Unauthorized");
        } else if (error.status == 403) {
          this.toast.createToster("error", "Forbidden");
        } else if (error.status == 404) {
          this.toast.createToster("error", "Personal Parking Lot Not Found");
        } else if (error.status == 409) {
          this.toast.createToster(
            "error",
            "Active Subscription Cannot Be Deleted"
          );
        } else if (error.status == 500) {
          this.toast.createToster("error", "Server Error");
        } else if (error.status == 503) {
          this.toast.createToster("error", "Server Unavailable");
        } else this.toast.createToster("error", "Unknown Error");
        this.isLoading = false;
        this.activeModal.dismiss();
      }
    );
  }

  updateParkingLot(form: {
    value: {
      isHandicapParkingLot: string;
      newStreet: string;
      newNumberOfParkingLot: number;
      newPrice: number;
      vehicleType: string;
      newLatitude: string;
      newLongitude: string;
    };
  }) {
    let handicapBool: boolean;

    if (form.value.isHandicapParkingLot == "True") {
      handicapBool = true;
    } else if (form.value.isHandicapParkingLot == "False") {
      handicapBool = false;
    }

    const body = {
      newStreet: form.value.newStreet,
      newNumberOfParkingLot: form.value.newNumberOfParkingLot,
      newIsHandicapParkingLot: handicapBool,
      newPrice: form.value.newPrice,
      newTypeOfVehicle: form.value.vehicleType,
      newLatitude: form.value.newLatitude,
      newLongitude: form.value.newLongitude,
      oldStreet: this.PARK.street,
      oldNumberOfParkingLot: this.PARK.numberOfParkingLot,
    };

    console.log(body);

    this.parkingLotService.updatePersonalParkingLot(body).subscribe(
      () => {
        this.toast.createToster("success", "Personal Parking Lot Updated");
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 400) {
          this.toast.createToster("error", "Bad Request");
        } else if (error.status == 401) {
          this.toast.createToster("error", "Unauthorized");
        } else if (error.status == 403) {
          this.toast.createToster("error", "Forbidden");
        } else if (error.status == 404) {
          this.toast.createToster("error", "Personal Parking Lot Not Found");
        } else if (error.status == 409) {
          this.toast.createToster(
            "error",
            "Active Subscription Cannot Be Updated"
          );
        } else if (error.status == 500) {
          this.toast.createToster("error", "Server Error");
        } else if (error.status == 503) {
          this.toast.createToster("error", "Server Unavailable");
        } else this.toast.createToster("error", "Unknown Error");
        this.isLoading = false;
        this.activeModal.dismiss();
      }
    );
  }
}
