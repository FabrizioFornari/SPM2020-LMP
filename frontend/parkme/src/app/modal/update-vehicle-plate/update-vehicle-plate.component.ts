import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';
import { AccountManagementService } from 'src/app/services/account-management.service';

@Component({
  selector: 'app-update-vehicle-plate',
  templateUrl: './update-vehicle-plate.component.html',
  styleUrls: ['./update-vehicle-plate.component.css'],
})
export class UpdateVehiclePlateComponent implements OnInit {
  @Input() PLATE: string;
  @Input() VEHICLE: string;

  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];

  newPlate: string = "";
  newVehicle: string = "";

  newPlateError: boolean = false;
  newVehicleError: boolean = false;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private toastrService: ToastrService,
    private acManVal: AccountManagementValidatorService,
    private accManSer: AccountManagementService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  updatePlateVehicle(form: { value: { newPlate: string; newVehicle: string } }) {
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
        newPlate: body.newPlate ,
        currentVehicleType: this.VEHICLE,
        newVehicleType: body.newVehicle
      }
      this.accManSer.updateVechiclePlate(body2).subscribe(
        () => {
          this.toastrService.success('Successfully Updated Plate/Vehicle');
          this.isLoading = false;
          this.activeModal.dismiss();
          localStorage.clear();
          this.router.navigate(["/login"]);
        },
        (error) => {
          if (error.status == 401) {
            this.toastrService.warning('Bad Credentials');
          } else if (error.status == 403){
            this.toastrService.warning('Forbidden');
          } else if (error.status == 500){
            this.toastrService.warning('Server Error');
          } else if (error.status == 226){
            this.toastrService.warning('Email Already in Use');
          } else {
            this.toastrService.warning('Unknown Error');
          }
          this.isLoading = false;
        }
      );
    } else {
      return null;
    }
  }
}
