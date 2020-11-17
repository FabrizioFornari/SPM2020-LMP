import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';

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
    private acManVal: AccountManagementValidatorService
  ) {}

  ngOnInit(): void {}

  updatePlateVehicle(form: { value: { newPlate: any; newVehicle: any } }) {
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
      this.toastrService.success('Plate/Vehicle Updated');
      console.table(body);
      this.activeModal.dismiss();
      this.isLoading = false;
    } else {
      return null;
    }
  }
}
