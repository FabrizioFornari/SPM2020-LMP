import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-open-handicap-request',
  templateUrl: './open-handicap-request.component.html',
  styleUrls: ['./open-handicap-request.component.css'],
})
export class OpenHandicapRequestComponent implements OnInit {
  @Input() REQUEST: any;

  constructor(
    public activeModal: NgbActiveModal,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {
    console.log("Request from modal" + JSON.stringify(this.REQUEST));
  }
}
