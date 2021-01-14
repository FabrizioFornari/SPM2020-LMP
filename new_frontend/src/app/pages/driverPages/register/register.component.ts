import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { InputValidatorService } from 'src/app/services/commonServices/validator/input-validator.service';
import { DriverRegisterService } from 'src/app/services/driverServices/driver-register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  username: string = '';
  name: string = '';
  surname: string = '';
  ssn: string = '';
  email: string = '';
  phone: string = '';
  plate: string = '';
  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];
  vehicle: string = '';
  password: string = '';
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
    private titleService: Title,
    private driverRegister: DriverRegisterService,
    private registerValidate: InputValidatorService,
    private toast: NgxToastService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Register');
  }

  async onRegisterSubmit() {
    const user = {
      username: this.email,
      firstName: this.name,
      lastName: this.surname,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      plate: this.plate,
      vehicleType: this.vehicle,
      password: this.password,
    };

    if (this.registerValidate.validateName(user.firstName)) {
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
      this.driverRegister.register(user).subscribe(
        () => {
          this.toast.createToster('success', 'Registration Success');
          this.isLoading = false;
          this.router.navigate(['login']);
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
            this.toast.createToster('error', 'Email Already In Use');
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
