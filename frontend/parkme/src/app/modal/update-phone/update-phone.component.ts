import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';
import { AccountManagementService } from 'src/app/services/account-management.service';

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
    private acManVal: AccountManagementValidatorService,
    private toastrService: ToastrService,
    private accManSer: AccountManagementService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  updatePhone(form: { value: { newPhone: string } }) {

    const body = {
      newPhone: form.value.newPhone
    }

    if (this.acManVal.validatePhone(body.newPhone)) {
      this.newPhoneError = false;
    } else {
      this.newPhoneError = true;
    }


    if (!this.newPhoneError) {
      this.isLoading = true;
      const body2 = {
        currentPhone: this.PHONE,
        newPhone: body.newPhone
      }
      this.accManSer.updatePhone(body2).subscribe(
        () => {
          this.toastrService.success('Successfully Updated Phone');
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
