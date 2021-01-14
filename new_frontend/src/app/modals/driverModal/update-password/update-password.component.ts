import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountManagementService } from 'src/app/services/commonServices/account-management.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { InputValidatorService } from 'src/app/services/commonServices/validator/input-validator.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css'],
})
export class UpdatePasswordComponent implements OnInit {
  oldPassword: string = '';
  newPassword: string = '';
  newPasswordRepeated: string = '';

  oldPasswordError: boolean = false;
  newPasswordError: boolean = false;
  repeatNewPasswordError: boolean = false;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private acManVal: InputValidatorService,
    private toast: NgxToastService,
    private accManSer: AccountManagementService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  updatePassword(form: {
    value: {
      oldPassword: string;
      newPassword: string;
      newPasswordRepeated: string;
    };
  }) {
    const body = {
      oldPassword: form.value.oldPassword,
      newPassword: form.value.newPassword,
      newPasswordRepeated: form.value.newPasswordRepeated,
    };
    if (this.acManVal.validatePassword(body.oldPassword)) {
      this.oldPasswordError = false;
    } else {
      this.oldPasswordError = true;
    }

    if (this.acManVal.validatePassword(body.newPassword)) {
      this.newPasswordError = false;
    } else {
      this.newPasswordError = true;
    }

    if (body.newPasswordRepeated == body.newPassword) {
      this.repeatNewPasswordError = false;
    } else {
      this.repeatNewPasswordError = true;
    }

    if (
      !this.oldPasswordError &&
      !this.newPasswordError &&
      !this.repeatNewPasswordError
    ) {
      this.isLoading = true;
      const body2 = {
        currentPassword: body.oldPassword,
        newPassword: body.newPassword,
      };
      this.accManSer.updatePassword(body2).subscribe(
        () => {
          this.toast.createToster('success', 'Successfully Updated Password');
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
