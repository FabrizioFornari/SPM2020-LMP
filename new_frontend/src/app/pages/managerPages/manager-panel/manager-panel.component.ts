import { Component, OnInit } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { Router } from "@angular/router";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { AddParkingLotComponent } from "src/app/modals/managerModal/add-parking-lot/add-parking-lot.component";
import { AddPersonalParkingLotComponent } from "src/app/modals/managerModal/add-personal-parking-lot/add-personal-parking-lot.component";

@Component({
  selector: "app-manager-panel",
  templateUrl: "./manager-panel.component.html",
  styleUrls: ["./manager-panel.component.css"],
})
export class ManagerPanelComponent implements OnInit {
  constructor(
    private titleService: Title,
    private router: Router,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle("ParkMe | Manager");
  }

  parkingLotsList() {
    this.router.navigate(["manager-panel/parking-lots"]);
  }

  addParkingLot() {
    const modalRef = this.modalService.open(AddParkingLotComponent);
    modalRef.result.then(
      () => {
        console.log("Modal AddParkingLot Closed");
      },
      () => {
        console.log("Modal AddParkingLot Dismissed");
      }
    );
  }

  addPersonalParkingLot() {
    const modalRef = this.modalService.open(AddPersonalParkingLotComponent);
    modalRef.result.then(
      () => {
        console.log("Modal AddPersonalParkingLot Closed");
      },
      () => {
        console.log("Modal AddPersonalParkingLot Dismissed");
      }
    );
  }
}
