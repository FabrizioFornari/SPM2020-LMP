import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css'],
})
export class UpdatePasswordComponent implements OnInit {
  oldPassword: string;
  newPassword: string;
  newPasswordRepeated: string;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {}

  updatePassword(form: {
    value: {
      oldPassword: string;
      newPassword: string;
      newPasswordRepeated: string;
    };
  }) {
    console.log(`Old Password: ${form.value.oldPassword}`);
    console.log(`New Password: ${form.value.newPassword}`);
    console.log(`Repeated New Password: ${form.value.newPasswordRepeated}`);
    this.activeModal.dismiss();
  }
}
