import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-refund',
  templateUrl: './refund.component.html',
  styleUrls: ['./refund.component.css'],
})
export class RefundComponent implements OnInit {
  @Input() notification: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {}
}
