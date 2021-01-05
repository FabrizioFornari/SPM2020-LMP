import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

@Component({
  selector: 'app-parking-status',
  templateUrl: './parking-status.component.html',
  styleUrls: ['./parking-status.component.css']
})
export class ParkingStatusComponent implements OnInit {

  parkList = [];

  constructor(
    private titleService: Title,
    private parkingService: ParkingLotServiceService
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Parking Status');
    this.parkingService.vigilantGetParkingList().subscribe(
      (success) => {
        this.parkList = success;
      },
      (error) => {
        console.log(error);
        this.parkList = [];
      }
    )
    this.parkList = [
      {
        "via": "Aldo Moro"
      },
      {
        "via": "Madonna delle Carceri"
      },
      {
        "via": "Garibaldi"
      }
    ]
  }

  seeStatus(via){
    alert(via);
  }

}
