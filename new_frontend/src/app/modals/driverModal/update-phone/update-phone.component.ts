import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountManagementService } from 'src/app/services/commonServices/account-management.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { InputValidatorService } from 'src/app/services/commonServices/validator/input-validator.service';

@Component({
  selector: 'app-update-phone',
  templateUrl: './update-phone.component.html',
  styleUrls: ['./update-phone.component.css'],
})
export class UpdatePhoneComponent implements OnInit {
  @Input() PHONE: string;
  newPhone: string;

  newPhoneError: boolean = false;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private acManVal: InputValidatorService,
    private toast: NgxToastService,
    private accManSer: AccountManagementService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  updatePhone(form: { value: { newPhone: string } }) {
    const body = {
      newPhone: form.value.newPhone,
    };

    if (this.acManVal.validatePhone(body.newPhone)) {
      this.newPhoneError = false;
    } else {
      this.newPhoneError = true;
    }

    if (!this.newPhoneError) {
      this.isLoading = true;
      const body2 = {
        currentPhone: this.PHONE,
        newPhone: body.newPhone,
      };
      this.accManSer.updatePhone(body2).subscribe(
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
