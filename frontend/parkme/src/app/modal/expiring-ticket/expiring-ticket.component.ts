import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-expiring-ticket',
  templateUrl: './expiring-ticket.component.html',
  styleUrls: ['./expiring-ticket.component.css']
})
export class ExpiringTicketComponent implements OnInit {

  @Input() notification: any;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
