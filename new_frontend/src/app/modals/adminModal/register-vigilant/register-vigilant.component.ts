import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { VigilantRegisterService } from 'src/app/services/adminServices/vigilant-register.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { InputValidatorService } from 'src/app/services/commonServices/validator/input-validator.service';

@Component({
  selector: 'app-register-vigilant',
  templateUrl: './register-vigilant.component.html',
  styleUrls: ['./register-vigilant.component.css'],
})
export class RegisterVigilantComponent implements OnInit {
  firstName: string = '';
  lastName: string = '';
  ssn: string = '';
  email: string = '';
  phone: string = '';
  password: string = '';

  firstNameError: boolean = false;
  lastNameError: boolean = false;
  ssnError: boolean = false;
  emailError: boolean = false;
  phoneError: boolean = false;
  passwordError: boolean = false;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private toast: NgxToastService,
    private validator: InputValidatorService,
    private vigilantRegister: VigilantRegisterService
  ) {}

  ngOnInit(): void {}

  registerManager() {
    const user = {
      username: this.email,
      firstName: this.firstName,
      lastName: this.lastName,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      password: this.password,
    };

    if (this.validator.validateName(user.firstName)) {
      this.firstNameError = false;
    } else {
      this.firstNameError = true;
    }

    if (this.validator.validateSurname(user.lastName)) {
      this.lastNameError = false;
    } else {
      this.lastNameError = true;
    }

    if (this.validator.validateSSN(user.ssn)) {
      this.ssnError = false;
    } else {
      this.ssnError = true;
    }

    if (this.validator.validateEmail(user.email)) {
      this.emailError = false;
    } else {
      this.emailError = true;
    }

    if (this.validator.validatePhone(user.phone)) {
      this.phoneError = false;
    } else {
      this.phoneError = true;
    }

    if (this.validator.validatePassword(user.password)) {
      this.passwordError = false;
    } else {
      this.passwordError = true;
    }

    if (
      !this.firstNameError &&
      !this.lastNameError &&
      !this.ssnError &&
      !this.emailError &&
      !this.phoneError &&
      !this.passwordError
    ) {
      this.isLoading = true;

      this.vigilantRegister.vgRegister(user).subscribe(
        () => {
          this.toast.createToster(
            'success',
            'Vigilant Successfully Registered'
          );
          this.activeModal.close();
          this.isLoading = false;
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
