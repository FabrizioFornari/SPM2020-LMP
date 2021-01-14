import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-refund-component',
  templateUrl: './refund-component.component.html',
  styleUrls: ['./refund-component.component.css'],
})
export class RefundComponentComponent implements OnInit {
  @Input() notification: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {}
}
