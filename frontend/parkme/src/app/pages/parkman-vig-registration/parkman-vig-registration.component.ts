import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';
import { ParkingManagerVigilantRegistrationValidationService } from 'src/app/services/parking-manager-vigilant-registration-validation.service';
import { ParkingManagerVigilantRegistrationService } from 'src/app/services/parking-manager-vigilant-registration.service';

@Component({
  selector: 'app-parkman-vig-registration',
  templateUrl: './parkman-vig-registration.component.html',
  styleUrls: ['./parkman-vig-registration.component.css'],
})
export class ParkmanVigRegistrationComponent implements OnInit {
  accountType: Array<String> = ['Vigilant', 'Parking Manager'];
  account: string;
  firstName: string;
  lastName: string;
  ssn: string;
  email: string;
  phone: string;
  password: string;

  accountError: boolean = false;
  firstNameError: boolean = false;
  lastNameError: boolean = false;
  ssnError: boolean = false;
  emailError: boolean = false;
  phoneError: boolean = false;
  passwordError: boolean = false;

  isLoading: boolean = false;

  constructor(
    private pmVgRegistrationValidator: ParkingManagerVigilantRegistrationValidationService,
    private pmVgRegistrationService: ParkingManagerVigilantRegistrationService,
    private toastrService: ToastrService,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | PM-VIG Register');
  }

  onRegisterSubmit() {
    const user = {
      username: this.email,
      firstName: this.firstName,
      lastName: this.lastName,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      password: this.password,
    };

    if (this.pmVgRegistrationValidator.validateAccount(this.account)) {
      this.accountError = false;
    } else {
      this.accountError = true;
    }

    if (this.pmVgRegistrationValidator.validateName(user.firstName)) {
      this.firstNameError = false;
    } else {
      this.firstNameError = true;
    }

    if (this.pmVgRegistrationValidator.validateSurname(user.lastName)) {
      this.lastNameError = false;
    } else {
      this.lastNameError = true;
    }

    if (this.pmVgRegistrationValidator.validateSSN(user.ssn)) {
      this.ssnError = false;
    } else {
      this.ssnError = true;
    }

    if (this.pmVgRegistrationValidator.validateEmail(user.email)) {
      this.emailError = false;
    } else {
      this.emailError = true;
    }

    if (this.pmVgRegistrationValidator.validatePhone(user.phone)) {
      this.phoneError = false;
    } else {
      this.phoneError = true;
    }

    if (this.pmVgRegistrationValidator.validatePassword(user.password)) {
      this.passwordError = false;
    } else {
      this.passwordError = true;
    }

    if (
      !this.accountError &&
      !this.firstNameError &&
      !this.lastNameError &&
      !this.ssnError &&
      !this.emailError &&
      !this.phoneError &&
      !this.passwordError
    ) {
      this.isLoading = true;
      if (this.account == 'Vigilant') {
        this.pmVgRegistrationService.vRegister(user).subscribe(
          () => {
            this.toastrService.success(
              `${this.account} Successfully Registered`
            );
            this.isLoading = false;
          },
          (error) => {
            if (error.status == 401) {
              this.toastrService.warning('Bad Credentials');
            } else if (error.status == 403) {
              this.toastrService.warning('Forbidden');
            } else if (error.status == 500) {
              this.toastrService.warning('Server Error');
            } else if (error.status == 226) {
              this.toastrService.warning('Email Already in Use');
            } else if ((error.status = 201)) {
              this.toastrService.success(
                `${this.account} Successfully Registered`
              );
            } else {
              this.toastrService.warning('Unknown Error');
            }
            this.isLoading = false;
          }
        );
      } else if (this.account == 'Parking Manager') {
        this.pmVgRegistrationService.pmRegister(user).subscribe(
          () => {
            this.toastrService.success(
              `${this.account} Successfully Registered`
            );
            this.isLoading = false;
          },
          (error) => {
            console.log(error);
            if (error.status == 401) {
              this.toastrService.warning('Bad Credentials');
            } else if (error.status == 403) {
              this.toastrService.warning('Forbidden');
            } else if (error.status == 500) {
              this.toastrService.warning('Server Error');
            } else if (error.status == 226) {
              this.toastrService.warning('Email Already in Use');
            } else if ((error.status = 201)) {
              this.toastrService.success(
                `${this.account} Successfully Registered`
              );
            } else {
              this.toastrService.warning('Unknown Error');
            }
            this.isLoading = false;
          }
        );
      } else {
        return null;
      }
    } else {
      return null;
    }
  }
}
