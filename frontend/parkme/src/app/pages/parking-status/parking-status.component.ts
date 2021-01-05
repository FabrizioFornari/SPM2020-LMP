import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

@Component({
  selector: 'app-parking-status',
  templateUrl: './parking-status.component.html',
  styleUrls: ['./parking-status.component.css']
})
export class ParkingStatusComponent implements OnInit {

  streetList = [];
  parkList = [];
  parkInfo = [];

  showStreets: boolean = true;

  constructor(
    private titleService: Title,
    private parkingService: ParkingLotServiceService
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
        this.parkInfo = success;
        console.table(this.parkInfo);
      },
      (error) => {
        this.parkInfo = [];
        console.log(error);
      }
    )
  }

}
