import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/commonServices/login.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { InputValidatorService } from 'src/app/services/commonServices/validator/input-validator.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string = '';
  password: string = '';
  emailError: boolean = false;
  passwordError: boolean = false;
  isLoading: boolean = false;

  constructor(
    private titleService: Title,
    private loginService: LoginService,
    private loginValidator: InputValidatorService,
    private router: Router,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Login');
  }

  async onLoginSubmit() {
    const user = {
      email: this.email,
      password: this.password,
    };

    if (this.loginValidator.validateEmail(user.email)) {
      this.emailError = false;
    } else {
      this.emailError = true;
    }

    if (this.loginValidator.validatePassword(user.password)) {
      this.passwordError = false;
    } else {
      this.passwordError = true;
    }

    if (!this.emailError && !this.passwordError) {
      this.isLoading = true;
      this.loginService.login(user).subscribe(
        (data) => {
          localStorage.clear();
          localStorage.setItem('token', data.token);
          localStorage.setItem('user', JSON.stringify(data));
          this.isLoading = false;
          this.router.navigate(['/profile']).then(() => {
            window.location.reload();
          });
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
