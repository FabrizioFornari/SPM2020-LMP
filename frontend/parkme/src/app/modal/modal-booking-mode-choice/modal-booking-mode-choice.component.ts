import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-booking-mode-choice',
  templateUrl: './modal-booking-mode-choice.component.html',
  styleUrls: ['./modal-booking-mode-choice.component.css'],
})
export class ModalBookingModeChoiceComponent implements OnInit {
  constructor(public activeModal: NgbActiveModal, private router: Router) {}

  ngOnInit(): void {}

  manual() {
    this.router.navigate(['/map', {state: {automatic: false}}]);
    this.activeModal.dismiss();
  }

  automatic() {
    this.router.navigate(['/map', {state: {automatic: true}}]);
    this.activeModal.dismiss();
  }
}
