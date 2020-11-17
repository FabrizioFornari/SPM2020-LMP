import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';

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
    private toastrService: ToastrService
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
      this.toastrService.success('Email Updated');
      console.table(body);
      this.activeModal.dismiss();
      this.isLoading = false;
    } else {
      return null;
    }
  }
}
