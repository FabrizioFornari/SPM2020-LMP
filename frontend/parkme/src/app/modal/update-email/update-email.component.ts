import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';
import { AccountManagementService } from 'src/app/services/account-management.service';

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
    private accManValid: AccountManagementValidatorService,
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
          this.toastrService.success('Successfully Updated Email');
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
