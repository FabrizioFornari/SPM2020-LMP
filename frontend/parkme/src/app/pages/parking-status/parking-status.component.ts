import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-parking-status',
  templateUrl: './parking-status.component.html',
  styleUrls: ['./parking-status.component.css']
})
export class ParkingStatusComponent implements OnInit {

  parkList = [];

  constructor(
    private titleService: Title
  ) { }

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Parking Status');
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
