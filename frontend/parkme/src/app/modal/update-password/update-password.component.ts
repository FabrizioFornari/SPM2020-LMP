import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';
import { AccountManagementService } from 'src/app/services/account-management.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css'],
})
export class UpdatePasswordComponent implements OnInit {
  oldPassword: string = "";
  newPassword: string = "";
  newPasswordRepeated: string = "";

  oldPasswordError: boolean = false;
  newPasswordError: boolean = false;
  repeatNewPasswordError: boolean = false;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private acManVal: AccountManagementValidatorService,
    private toastrService: ToastrService,
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
      newPasswordRepeated: form.value.newPasswordRepeated
    }
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

    if (!this.oldPasswordError && !this.newPasswordError && !this.repeatNewPasswordError) {
      this.isLoading = true;
      const body2 = {
        currentPassword: body.oldPassword,
        newPassword: body.newPassword
      }
      this.accManSer.updatePassword(body2).subscribe(
        () => {
          this.toastrService.success('Successfully Updated Password');
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
