import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AccountManagementValidatorService } from 'src/app/services/account-management-validator.service';

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
    private toastrService: ToastrService
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
      this.toastrService.success('Phone Updated');
      console.table(body);
      this.activeModal.dismiss();
      this.isLoading = false;
    } else {
      return null;
    }
  }
}
