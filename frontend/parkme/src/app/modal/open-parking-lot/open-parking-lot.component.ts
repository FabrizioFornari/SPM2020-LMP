import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-open-parking-lot',
  templateUrl: './open-parking-lot.component.html',
  styleUrls: ['./open-parking-lot.component.css'],
})
export class OpenParkingLotComponent implements OnInit {
  @Input() PARK: any;

  isLoading: boolean = false;

  handicapType: Array<String> = ['True', 'False'];
  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];

  newStreet: string = '';
  newNumberOfParkingLot: string = '';
  newIsHandicapParkingLot: string = '';
  newPricePerHours: number = 0;
  newTypeOfVehicle: string = '';
  newLatitude: string = '';
  newLongitude: string = '';

  constructor(
    public activeModal: NgbActiveModal,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {}

  updateParking(form: {
    value: {
      isHandicapParkingLot: string;
      newStreet: string;
      newNumberOfParkingLot: string;
      newPricePerHours: number;
      vehicleType: string;
      newLatitude: string;
      newLongitude: string;
    };
  }) {
    let handicapBool: boolean;

    if (form.value.isHandicapParkingLot == 'True') {
      handicapBool = true;
    } else {
      handicapBool = false;
    }

    const body = {
      street: form.value.newStreet,
      numberOfParkingLot: form.value.newNumberOfParkingLot,
      isHandicapParkingLot: handicapBool,
      pricePerHours: form.value.newPricePerHours,
      typeOfVehicle: form.value.vehicleType,
      coordinates: {
        latitude: form.value.newLatitude,
        longitude: form.value.newLongitude,
      },
    };

    console.table(body);
    this.activeModal.dismiss();
  }
}
