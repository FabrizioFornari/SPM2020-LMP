import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-update-phone',
  templateUrl: './update-phone.component.html',
  styleUrls: ['./update-phone.component.css']
})
export class UpdatePhoneComponent implements OnInit {

  @Input() PHONE: string;
  newPhone: string;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  updatePhone(form: { value: { newPhone: string; }; }){
    console.log(`New Phone: ${form.value.newPhone}`);
    this.activeModal.dismiss();
  }

}
