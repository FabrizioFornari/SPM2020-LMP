import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountManagementService } from 'src/app/services/commonServices/account-management.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { InputValidatorService } from 'src/app/services/commonServices/validator/input-validator.service';

@Component({
  selector: 'app-update-vehicle',
  templateUrl: './update-vehicle.component.html',
  styleUrls: ['./update-vehicle.component.css'],
})
export class UpdateVehicleComponent implements OnInit {
  @Input() PLATE: string;
  @Input() VEHICLE: string;

  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];

  newPlate: string = '';
  newVehicle: string = '';

  newPlateError: boolean = false;
  newVehicleError: boolean = false;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private toast: NgxToastService,
    private acManVal: InputValidatorService,
    private accManSer: AccountManagementService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  updatePlateVehicle(form: {
    value: { newPlate: string; newVehicle: string };
  }) {
    const body = {
      newPlate: form.value.newPlate,
      newVehicle: form.value.newVehicle,
    };

    if (this.acManVal.validatePlate(body.newPlate)) {
      this.newPlateError = false;
    } else {
      this.newPlateError = true;
    }

    if (this.acManVal.validateVehicle(body.newVehicle)) {
      this.newVehicleError = false;
    } else {
      this.newVehicleError = true;
    }

    if (!this.newPlateError && !this.newVehicleError) {
      this.isLoading = true;

      const body2 = {
        currentPlate: this.PLATE,
        newPlate: body.newPlate,
        currentVehicleType: this.VEHICLE,
        newVehicleType: body.newVehicle,
      };
      this.accManSer.updateVechiclePlate(body2).subscribe(
        () => {
          this.toast.createToster(
            'success',
            'Successfully Updated Plate/Vehicle'
          );
          this.isLoading = false;
          this.activeModal.dismiss();
          localStorage.clear();
          this.router.navigate(['/login']);
        },
        (error) => {
          if (error.status == 400) {
            this.toast.createToster('error', 'Bad Request');
          } else if (error.status == 401) {
            this.toast.createToster('error', 'Unauthorized');
          } else if (error.status == 403) {
            this.toast.createToster('error', 'Forbidden');
          } else if (error.status == 404) {
            this.toast.createToster('error', 'Not Found');
          } else if (error.status == 409) {
            this.toast.createToster('error', 'Conflict');
          } else if (error.status == 500) {
            this.toast.createToster('error', 'Server Error');
          } else if (error.status == 503) {
            this.toast.createToster('error', 'Server Unavailable');
          } else this.toast.createToster('error', 'Unknown Error');

          this.isLoading = false;
        }
      );
    } else {
      return null;
    }
  }
}
