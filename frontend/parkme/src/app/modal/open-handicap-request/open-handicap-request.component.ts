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

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {}

  acceptRequest(req: { username: any; }){
    this.isLoading = true;
    this.toastrService.success(`${req.username} accepted`);
    this.isLoading = false;
    this.activeModal.dismiss();
  }

  declineRequest(req: { username: any; }){
    this.isLoading = true;
    this.toastrService.warning(`${req.username} declined`);
    this.isLoading = false;
    this.activeModal.dismiss();
  }
}
