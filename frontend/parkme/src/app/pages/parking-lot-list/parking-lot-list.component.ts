import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { InsertParkingLotComponent } from 'src/app/modal/insert-parking-lot/insert-parking-lot.component';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';
import { OpenParkingLotComponent } from 'src/app/modal/open-parking-lot/open-parking-lot.component';

interface Park {
  street: string;
  numberOfParkingLot: number;
  isHandicapParkingLot: boolean;
  pricePerHours: number;
  typeOfVehicle: string;
  coordinates: {
    latitude: number,
    longitude: number
  };
}

@Component({
  selector: 'app-parking-lot-list',
  templateUrl: './parking-lot-list.component.html',
  styleUrls: ['./parking-lot-list.component.css'],
})
export class ParkingLotListComponent implements OnInit {
  show: boolean = false;

  PARKS: Park[] = [];

  parks$: Observable<Park[]>;
  filter = new FormControl('');

  constructor(private modalService: NgbModal, private parkingService: ParkingLotServiceService) {
    this.parks$ = this.filter.valueChanges.pipe(
      startWith(''),
      map((text) => this.search(text))
    );
  }

  ngOnInit(): void {
    this.updateEntry();
  }

  addParkingLot() {
    const modalRef = this.modalService.open(InsertParkingLotComponent);
    modalRef.result.then(
      () => {
        console.log('Modal Insert Closed');
        this.updateEntry();
      },
      () => {
        console.log('Modal Insert Closed');
        this.updateEntry();
      }
    );
  }

  updateEntry() {
    this.show = true;
     this.show = false;
    this.parkingService.getList().subscribe(
      (data) => {
        this.PARKS = data;
        this.show = true;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  search(text: string): Park[] {
    return this.PARKS.filter((park) => {
      const term = text.toLowerCase();
      return (
        park.street.toLowerCase().includes(term)
      );
    });
  }

  openRequest(park: any) {
    const modalRef = this.modalService.open(OpenParkingLotComponent);
    modalRef.componentInstance.PARK = park;
    modalRef.result.then(
      () => {
        console.log('Modal Request Closed');
        this.updateEntry();
      },
      () => {
        console.log('Modal Request Closed');
        this.updateEntry();
      }
    );
  }
}
