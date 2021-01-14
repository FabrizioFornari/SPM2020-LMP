import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-select-booking-type',
  templateUrl: './select-booking-type.component.html',
  styleUrls: ['./select-booking-type.component.css'],
})
export class SelectBookingTypeComponent implements OnInit {
  constructor(public activeModal: NgbActiveModal, private router: Router) {}

  ngOnInit(): void {}

  manual() {
    this.router.navigateByUrl('/map', { state: { automatic: false } });
    this.activeModal.dismiss();
  }

  automatic() {
    this.router.navigateByUrl('/map', { state: { automatic: true } });
    this.activeModal.dismiss();
  }
}
