import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

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

  handicapType: Array<String> = ['True', 'False'];

  street: string = '';
  numberOfParkingLot: number = 0;
  isHandicapParkingLot: string = '';
  pricePerHours: number = 0;
  typeOfVehicle: string = '';
  latitude: string = "";
  longitude: string = "";

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private parkingLotService: ParkingLotServiceService,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {}

  insertParkingLot(form: {
    value: {
      street: string;
      numberOfParkingLot: number;
      isHandicapParkingLot: string;
      pricePerHours: number;
      typeOfVehicle: string;
      latitude: string;
      longitude: string;
    };
  }) {
    this.isLoading = true;
    let handicapBool: boolean;
    if (form.value.isHandicapParkingLot == 'False') {
      handicapBool = false;
    } else {
      handicapBool = true;
    }
    const body = {
      street: form.value.street,
      numberOfParkingLot: form.value.numberOfParkingLot,
      isHandicapParkingLot: handicapBool,
      pricePerHours: form.value.pricePerHours,
      typeOfVehicle: form.value.typeOfVehicle,
      coordinates: {
        latitude: form.value.latitude,
        longitude: form.value.longitude,
      },
    };

    this.parkingLotService.insertParking(body).subscribe(
      () => {
        this.toastrService.success('Parking Lot Successfully Added');
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 409) {
          this.toastrService.warning('Parking Lot Number Already Used');
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
