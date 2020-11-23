import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-insert-parking-lot',
  templateUrl: './insert-parking-lot.component.html',
  styleUrls: ['./insert-parking-lot.component.css'],
})
export class InsertParkingLotComponent implements OnInit {
  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];

  handicapType: Array<String> = ['Yes', 'No'];

  street: string = '';
  number: string = '';
  handicap: string = '';
  price: string = '';
  vehicle: string = '';
  coordinates: string = '';

  isLoading: boolean = false;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {}

  insertParkingLot(form: {
    value: {
      street: string;
      number: string;
      handicap: string;
      price: string;
      vehicle: string;
      coordinates: string;
    };
  }) {
    this.isLoading = true;
    const body = {
      street: form.value.street,
      number: form.value.number,
      isHandicap: form.value.handicap,
      price: form.value.price,
      vehicleType: form.value.vehicle,
      coordinates: form.value.coordinates,
    };

    console.table(body);
    this.isLoading = false;
    this.activeModal.dismiss();
  }
}
