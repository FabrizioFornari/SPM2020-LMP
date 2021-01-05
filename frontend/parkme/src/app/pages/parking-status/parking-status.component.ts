import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ParkInfoComponent } from 'src/app/modal/park-info/park-info.component';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

@Component({
  selector: 'app-parking-status',
  templateUrl: './parking-status.component.html',
  styleUrls: ['./parking-status.component.css']
})
export class ParkingStatusComponent implements OnInit {

  streetList = [];
  parkList = [];

  showStreets: boolean = true;

  constructor(
    private titleService: Title,
    private parkingService: ParkingLotServiceService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Parking Status');
    this.parkingService.vigilantGetStreetList().subscribe(
      (success) => {
        this.streetList = success;
      },
      (error) => {
        console.log(error);
        this.streetList = [];
      }
    )
  }

  seeStreet(street){
    this.parkingService.vigilantGetParksStreet(street).subscribe(
      (success) => {
        console.log(success);
        this.parkList = success;
        this.showStreets = false;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  backButton(){
    window.location.reload();
  }

  seeStatus(street, number){
    this.parkingService.vigilantGetParkInfo(street, number).subscribe(
      (success) => {
        this.modalInfo(success);
      },
      (error) => {
        console.log(error);
      }
    )
  }

  modalInfo(info){
    const modalRef = this.modalService.open(ParkInfoComponent);
    modalRef.componentInstance.INFO = info;
    modalRef.result.then(
      () => {
        console.log('Modal Request Closed');
      },
      () => {
        console.log('Modal Request Closed');
      }
    );
  }

}
