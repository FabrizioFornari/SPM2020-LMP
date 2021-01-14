import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AccountManagementService } from 'src/app/services/commonServices/account-management.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { InputValidatorService } from 'src/app/services/commonServices/validator/input-validator.service';

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
    private toast: NgxToastService,
    private accManValid: InputValidatorService,
    private accManSer: AccountManagementService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  updateEmail(form: { value: { newEmail: string } }) {
    const body = {
      currentEmail: this.EMAIL,
      newEmail: form.value.newEmail,
    };

    if (this.accManValid.validateEmail(body.newEmail)) {
      this.emailError = false;
    } else {
      this.emailError = true;
    }

    if (!this.emailError) {
      this.isLoading = true;
      console.table(body);
      this.accManSer.updateEmail(body).subscribe(
        () => {
          this.toast.createToster('success', 'Successfully Updated Email');
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
