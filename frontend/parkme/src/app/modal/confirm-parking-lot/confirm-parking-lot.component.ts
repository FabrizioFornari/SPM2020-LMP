import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-confirm-parking-lot',
  templateUrl: './confirm-parking-lot.component.html',
  styleUrls: ['./confirm-parking-lot.component.css']
})
export class ConfirmParkingLotComponent implements OnInit {
  @Input() STREET;

  constructor(
    public activeModal: NgbActiveModal,) { }

  ngOnInit(): void {
  }

}
