import { Component, OnInit } from '@angular/core';
import { DriverAuthService } from 'src/app/services/driver-auth.service';
import { DriverRegistrationValidationService } from 'src/app/services/driver-registration-validation.service';
import { ToastrService } from "ngx-toastr";
@Component({
  selector: 'app-driver-registration',
  templateUrl: './driver-registration.component.html',
  styleUrls: ['./driver-registration.component.css'],
})
export class DriverRegistrationComponent implements OnInit {
  name: string;
  surname: string;
  ssn: string;
  email: string;
  phone: number;
  plate: string;
  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];
  vehicle: string;
  password: string;

  nameError: boolean = false;
  surnameError: boolean = false;
  ssnError: boolean = false;
  emailError: boolean = false;
  phoneError: boolean = false;
  plateError: boolean = false;
  vehicleError: boolean = false;
  passwordError: boolean = false;

  isLoading: boolean = false;
  constructor(
    private driverAuthService: DriverAuthService,
    private registerValidate: DriverRegistrationValidationService,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {}

  onLoginSubmit() {
    this.isLoading = true;

    const user = {
      firstName: this.name,
      lastName: this.surname,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      plate: this.plate,
      vehicleType: this.vehicle,
      password: this.password,
    };

    if (this.registerValidate.validateName) {
      this.nameError = false;
    } else {
      this.nameError = true;
    }

    if (this.registerValidate.validateSurname) {
      this.surnameError = false;
    } else {
      this.surnameError = true;
    }

    if (this.registerValidate.validateSSN) {
      this.ssnError = false;
    } else {
      this.ssnError = true;
    }

    if (this.registerValidate.validateEmail) {
      this.emailError = false;
    } else {
      this.emailError = true;
    }

    if (this.registerValidate.validatePhone) {
      this.phoneError = false;
    } else {
      this.phoneError = true;
    }

    if (this.registerValidate.validatePlate) {
      this.plateError = false;
    } else {
      this.plateError = true;
    }

    if (this.registerValidate.validateVehicle) {
      this.vehicleError = false;
    } else {
      this.vehicleError = true;
    }

    if (this.registerValidate.validatePassword) {
      this.passwordError = false;
    } else {
      this.passwordError = true;
    }

    if (
      !this.nameError &&
      !this.surnameError &&
      !this.ssnError &&
      !this.emailError &&
      !this.phoneError &&
      !this.plateError &&
      !this.vehicleError &&
      !this.passwordError
    ) {
      this.driverAuthService.register(user).subscribe(
        (data) => {
          console.log(data);
          this.toastrService.success('Successfully Logged In');
          this.isLoading = false;
        },
        (error) => {
          console.log(error);
          this.isLoading = false;
        }
      );
    } else {
      return null;
    }
  }
}
