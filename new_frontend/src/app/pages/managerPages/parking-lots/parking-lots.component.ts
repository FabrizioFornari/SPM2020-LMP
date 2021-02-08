import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { OpenParkingLotComponent } from "src/app/modals/managerModal/open-parking-lot/open-parking-lot.component";
import { OpenPersonalParkingLotComponent } from "src/app/modals/managerModal/open-personal-parking-lot/open-personal-parking-lot.component";
import { NgxToastService } from "src/app/services/commonServices/ngx-toast.service";
import { ParkingLotServiceService } from "src/app/services/managerServices/parking-lot-service.service";

@Component({
  selector: "app-parking-lots",
  templateUrl: "./parking-lots.component.html",
  styleUrls: ["./parking-lots.component.css"],
})
export class ParkingLotsComponent implements OnInit {
  show: boolean = false;
  parkingLots = [];
  personalLots = [];

  constructor(
    private titleService: Title,
    private parkingLotService: ParkingLotServiceService,
    private modalService: NgbModal,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle("ParkMe | Manager");
    this.getAllParkingLots();
  }

  getAllParkingLots() {
    this.parkingLotService.getAllParkingLots().subscribe(
      (success) => {
        this.parkingLots = success.parkingLots;
        this.personalLots = success.personalParkingLots;
        this.show = true;
      },
      (error) => {
        if (error.status == 400) {
          this.toast.createToster("error", "Bad Request");
        } else if (error.status == 401) {
          this.toast.createToster("error", "Unauthorized");
        } else if (error.status == 403) {
          this.toast.createToster("error", "Forbidden");
        } else if (error.status == 404) {
          this.toast.createToster("error", "Not Found");
        } else if (error.status == 409) {
          this.toast.createToster("error", "Conflict");
        } else if (error.status == 500) {
          this.toast.createToster("error", "Server Error");
        } else if (error.status == 503) {
          this.toast.createToster("error", "Server Unavailable");
        } else this.toast.createToster("error", "Unknown Error");
        this.parkingLots = [];
        this.show = false;
      }
    );
  }

  openParkingLot(park) {
    const modalRef = this.modalService.open(OpenParkingLotComponent);
    modalRef.componentInstance.PARK = park;
    modalRef.result.then(
      () => {
        console.log("Modal OpenParkingLot Closed");
        this.getAllParkingLots();
      },
      () => {
        console.log("Modal OpenParkingLot Closed");
        this.getAllParkingLots();
      }
    );
  }

  openPersonal(park) {
    const modalRef = this.modalService.open(OpenPersonalParkingLotComponent);
    modalRef.componentInstance.PARK = park;
    modalRef.result.then(
      () => {
        console.log("Modal OpenPersonalParkingLot Closed");
        this.getAllParkingLots();
      },
      () => {
        console.log("Modal OpenPersonalParkingLot Closed");
        this.getAllParkingLots();
      }
    );
  }
}
