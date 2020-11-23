import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

interface Park {
  street: string;
  number: string;
  isHandicap: string;
  price: string;
  vehicleType: string,
  coordinates: string
}

@Component({
  selector: 'app-parking-lot-list',
  templateUrl: './parking-lot-list.component.html',
  styleUrls: ['./parking-lot-list.component.css']
})
export class ParkingLotListComponent implements OnInit {

  show: boolean = false;

  PARKS: Park[] = [
    {
      street: "Via Roma",
      number: "0101",
      isHandicap: "true",
      price: "€5",
      vehicleType: "4 Wheels Standard",
      coordinates: "	41,9109 ; 12,4818"
    },
    {
      street: "Via Milano",
      number: "0202",
      isHandicap: "false",
      price: "€4",
      vehicleType: "4 Wheels Big",
      coordinates: "45,4773 ; 9,1815"
    },
    {
      street: "Via Napoli",
      number: "0303",
      isHandicap: "true",
      price: "€6",
      vehicleType: "2 Wheels Standard",
      coordinates: "40,863 ; 14,2767"
    }
  ];

  parks$: Observable<Park[]>;
  filter = new FormControl('');

  constructor(private modalService: NgbModal) {
    this.parks$ = this.filter.valueChanges.pipe(
      startWith(''),
      map((text) => this.search(text))
    );
   }

  ngOnInit(): void {
    this.updateEntry();
  }

  updateEntry(){
    this.show = true;
    /* this.show = false;
    this.reqDown.downloadRequest().subscribe(
      (data) => {
        data.forEach(element => {
          element.timestamp = `${new Date(element.timestamp).toLocaleDateString("it-IT")} (${new Date(element.timestamp).toLocaleTimeString("it-IT")})`;
        });
        this.PERMITS = data;
        this.show = true;
        console.table(data);
      },
      (error) => {
        console.log(error);
      }
    ); */
  }

  search(text: string): Park[] {
    return this.PARKS.filter((park) => {
      const term = text.toLowerCase();
      return (
        park.street.toLowerCase().includes(term) ||
        park.number.includes(term) ||
        park.isHandicap.toLowerCase().includes(term) ||
        park.price.includes(term) ||
        park.vehicleType.toLowerCase().includes(term) ||
        park.coordinates.includes(term)
      );
    });
  }

  openRequest(park: any){
    alert(JSON.stringify(park));
    /* const modalRef = this.modalService.open(OpenHandicapRequestComponent);
    modalRef.componentInstance.REQUEST = permit;
    modalRef.result.then(
      () => {
        console.log('Modal Request Closed');
        this.updateEntry();
      },
      () => {
        console.log('Modal Request Closed');
        this.updateEntry();
      }
    ); */
  }

}
