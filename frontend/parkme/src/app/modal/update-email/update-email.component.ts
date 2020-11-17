import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-update-email',
  templateUrl: './update-email.component.html',
  styleUrls: ['./update-email.component.css']
})
export class UpdateEmailComponent implements OnInit {

  @Input() EMAIL: string;
  newEmail: string;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  updateEmail(form){
    console.log(`New Email: ${form.value.newEmail}`);
    this.activeModal.dismiss();
  }

}
