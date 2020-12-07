import { Component, OnInit } from '@angular/core';
import { DriverAuthService } from 'src/app/services/driver-auth.service';
import { DriverRegistrationValidationService } from 'src/app/services/driver-registration-validation.service';
import { ToastrService } from "ngx-toastr";
import { Title } from '@angular/platform-browser';

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
  phone: string;
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
    private toastrService: ToastrService,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Driver Register');
  }

  onLoginSubmit() {

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

    if (this.registerValidate.validateName(user.firstName)){
      this.nameError = false;
    } else {
      this.nameError = true;
    }

    if (this.registerValidate.validateSurname(user.lastName)) {
      this.surnameError = false;
    } else {
      this.surnameError = true;
    }

    if (this.registerValidate.validateSSN(user.ssn)) {
      this.ssnError = false;
    } else {
      this.ssnError = true;
    }

    if (this.registerValidate.validateEmail(user.email)) {
      this.emailError = false;
    } else {
      this.emailError = true;
    }

    if (this.registerValidate.validatePhone(user.phone)) {
      this.phoneError = false;
    } else {
      this.phoneError = true;
    }

    if (this.registerValidate.validatePlate(user.plate)) {
      this.plateError = false;
    } else {
      this.plateError = true;
    }

    if (this.registerValidate.validateVehicle(user.vehicleType)) {
      this.vehicleError = false;
    } else {
      this.vehicleError = true;
    }

    if (this.registerValidate.validatePassword(user.password)) {
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
      this.isLoading = true;
      this.driverAuthService.register(user).subscribe(
        () => {
          this.toastrService.success('Successfully Registered');
          this.isLoading = false;
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
