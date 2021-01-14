import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { ParkingLotServiceService } from 'src/app/services/managerServices/parking-lot-service.service';

@Component({
  selector: 'app-add-parking-lot',
  templateUrl: './add-parking-lot.component.html',
  styleUrls: ['./add-parking-lot.component.css'],
})
export class AddParkingLotComponent implements OnInit {
  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];

  handicapType: Array<String> = ['True', 'False'];

  street: string = '';
  numberOfParkingLot: number = 0;
  isHandicapParkingLot: string = '';
  pricePerHour: number = 0;
  typeOfVehicle: string = '';
  latitude: string = '';
  longitude: string = '';

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private parkingLotService: ParkingLotServiceService,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {}

  addParkingLot(form) {
    this.isLoading = true;
    let handicapBool: boolean;
    if (form.value.isHandicapParkingLot == 'False') {
      handicapBool = false;
    } else {
      handicapBool = true;
    }
    let body = {
      street: form.value.street,
      numberOfParkingLot: form.value.numberOfParkingLot,
      isHandicapParkingLot: handicapBool,
      pricePerHour: form.value.pricePerHour,
      typeOfVehicle: form.value.typeOfVehicle,
      coordinates: {
        latitude: form.value.latitude,
        longitude: form.value.longitude,
      },
    };

    this.parkingLotService.addParkingLot(body).subscribe(
      () => {
        this.toast.createToster('success', 'Parking Lot Successfully Added');
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        if (error.status == 400) {
          this.toast.createToster('error', 'Bad Request');
        } else if (error.status == 401) {
          this.toast.createToster('error', 'Unauthorized');
        } else if (error.status == 403) {
          this.toast.createToster('error', 'Forbidden');
        } else if (error.status == 404) {
          this.toast.createToster('error', 'Not Found');
        } else if (error.status == 409) {
          this.toast.createToster('error', 'Parking Lot Number Already Used');
        } else if (error.status == 500) {
          this.toast.createToster('error', 'Server Error');
        } else if (error.status == 503) {
          this.toast.createToster('error', 'Server Unavailable');
        } else this.toast.createToster('error', 'Unknown Error');

        this.isLoading = false;
      }
    );
  }
}
