import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { ParkInfoComponent } from "src/app/modals/vigilantModal/park-info/park-info.component";
import { SelectParkingTypeComponent } from "src/app/modals/vigilantModal/select-parking-type/select-parking-type.component";
import { ParkingLotService } from "src/app/services/vigilantServices/parking-lot.service";

@Component({
  selector: "app-parking-lot-list",
  templateUrl: "./parking-lot-list.component.html",
  styleUrls: ["./parking-lot-list.component.css"],
})
export class ParkingLotListComponent implements OnInit {
  streetList = [];
  parkList = [];
  showStreets: boolean = true;

  constructor(
    private titleService: Title,
    private parkingService: ParkingLotService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle("ParkMe | Parking Status");
    this.parkingService.vigilantGetStreetList().subscribe(
      (success) => {
        this.streetList = success;
      },
      (error) => {
        console.log(error);
        this.streetList = [];
      }
    );
  }

  selectType(street: string) {
    const modalRef = this.modalService.open(SelectParkingTypeComponent);
    modalRef.componentInstance.passEntry.subscribe((type: string) => {
      if (type == "public") {
        this.publicParks(street);
      } else if (type == "personal") {
        this.personalParks(street);
      }
    });
    modalRef.result.then(
      () => {
        console.log("Modal Select Parking Type Closed");
      },
      () => {
        console.log("Modal Select Parking Type  Dismissed");
      }
    );
  }

  publicParks(street) {
    this.parkingService.vigilantGetParksStreet(street).subscribe(
      (success) => {
        this.parkList = success;
        this.showStreets = false;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  personalParks(street) {
    this.parkingService.vigilantGetPersonalParkingLots(street).subscribe(
      (success) => {
        this.parkList = success;
        this.showStreets = false;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  backButton() {
    window.location.reload();
  }

  seeStatus(street, number) {
    this.parkingService.vigilantGetParkInfo(street, number).subscribe(
      (success) => {
        this.modalInfo(success);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  modalInfo(info) {
    const modalRef = this.modalService.open(ParkInfoComponent);
    modalRef.componentInstance.INFO = info;
    modalRef.result.then(
      () => {
        console.log("Modal Request Closed");
      },
      () => {
        console.log("Modal Request Closed");
      }
    );
  }
}
