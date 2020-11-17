import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';

@Component({
  selector: 'app-update-email',
  templateUrl: './update-email.component.html',
  styleUrls: ['./update-email.component.css'],
})
export class UpdateEmailComponent implements OnInit {
  @Input() EMAIL: string;
  newEmail: string;

  emailError: boolean = false;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private toastrService: ToastrService,
    private accManValid: AccountManagementValidatorService
  ) {}

  ngOnInit(): void {}

  updateEmail(form: { value: { newEmail: string } }) {
    const body = {
      email: form.value.newEmail,
    };

    if (this.accManValid.validateEmail(body.email)) {
      this.emailError = false;
    } else {
      this.emailError = true;
    }

    if (!this.emailError) {
      this.isLoading = true;
      this.toastrService.success('Email Updated');
      this.isLoading = false;
      console.log(`New Email: ${form.value.newEmail}`);
      this.activeModal.dismiss();
    } else {
      return null;
    }
  }
}
