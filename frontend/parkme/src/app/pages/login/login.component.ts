import { Component, OnInit } from '@angular/core';
import { UnifiedLoginService } from 'src/app/services/unified-login.service';
import { ToastrService } from 'ngx-toastr';
import { LoginFormValidationService } from 'src/app/services/login-form-validation.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string;
  password: string;
  emailError: boolean = false;
  passwordError: boolean = false;
  isLoading: boolean = false;

  constructor(
    private unifiedLogin: UnifiedLoginService,
    private toastrService: ToastrService,
    private loginValidator: LoginFormValidationService,
    private router: Router,
    private titleService: Title,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Login');
  }

  onLoginSubmit() {

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
      this.unifiedLogin.login(user).subscribe(
        (data) => {
          localStorage.clear();
          localStorage.setItem('token', data.token);
          localStorage.setItem('user', JSON.stringify(data));

          this.toastrService.success('Successfully Logged In');
          this.isLoading = false;
          this.router.navigate(['/account-info']).then(() => {
            window.location.reload();
          });
        },
        (error) => {
          if (error.status == 401) {
            this.toastrService.warning('Bad Credentials');
          } else if (error.status = 403){
            this.toastrService.warning('Forbidden');
          } else if (error.status = 500){
            this.toastrService.warning('Server Error');
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
