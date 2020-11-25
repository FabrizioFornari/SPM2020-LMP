import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

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
  newNumberOfParkingLot: number;
  newIsHandicapParkingLot: string = '';
  newPricePerHours: number;
  newTypeOfVehicle: string = '';
  newLatitude: string = '';
  newLongitude: string = '';

  constructor(
    public activeModal: NgbActiveModal,
    private toastrService: ToastrService,
    private parkingLotService: ParkingLotServiceService
  ) {}

  ngOnInit(): void {}

  deleteParking(park: { street: string; numberOfParkingLot: number }) {
    const body = {
      street: park.street,
      numberOfParkingLot: park.numberOfParkingLot,
    };
    this.parkingLotService.deleteParking(body).subscribe(
      () => {
        this.toastrService.success('Parking Lot Successfully Deleted');
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 404) {
          this.toastrService.warning('Parking Lot Does Not Exists');
        } else if (error.status == 401) {
          this.toastrService.warning('Unauthorized');
        } else if (error.status == 403) {
          this.toastrService.warning('Forbidden');
        } else if (error.status == 500) {
          this.toastrService.warning('Server Error');
        } else {
          this.toastrService.warning('Unknown Error');
        }
        this.isLoading = false;
        this.activeModal.dismiss();
      }
    );
  }

  updateParking(form: {
    value: {
      isHandicapParkingLot: string;
      newStreet: string;
      newNumberOfParkingLot: number;
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

    this.parkingLotService.updateParking(body).subscribe(
      () => {
        this.toastrService.success('Parking Lot Successfully Updated');
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 409) {
          this.toastrService.warning('Parking Lot Number Already Used');
        } else if (error.status == 401) {
          this.toastrService.warning('Unauthorized');
        } else if (error.status == 403) {
          this.toastrService.warning('Forbidden');
        } else if (error.status == 500) {
          this.toastrService.warning('Server Error');
        } else {
          this.toastrService.warning('Unknown Error');
        }
        this.isLoading = false;
        this.activeModal.dismiss();
      }
    );
  }
}
