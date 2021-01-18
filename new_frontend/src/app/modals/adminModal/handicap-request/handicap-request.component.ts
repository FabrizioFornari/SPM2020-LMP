import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { HandicapRequestService } from 'src/app/services/adminServices/handicap-request.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';

@Component({
  selector: 'app-handicap-request',
  templateUrl: './handicap-request.component.html',
  styleUrls: ['./handicap-request.component.css'],
})
export class HandicapRequestComponent implements OnInit {
  @Input() REQ: any;

  isLoading: boolean = false;

  constructor(
    public activeModal: NgbActiveModal,
    private handicapService: HandicapRequestService,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {}

  acceptRequest(username) {
    this.isLoading = true;
    this.handicapService.acceptRequest(username).subscribe(
      () => {
        this.toast.createToster('success', 'Successfully Accepted');
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 400) {
          this.toast.createToster('error', 'Bad Request');
        } else if (error.status == 401) {
          this.toast.createToster('error', 'Unauthorized');
        } else if (error.status == 403) {
          this.toast.createToster('error', 'Forbidden');
        } else if (error.status == 404) {
          this.toast.createToster('error', 'Not Found');
        } else if (error.status == 409) {
          this.toast.createToster('error', 'Conflict');
        } else if (error.status == 500) {
          this.toast.createToster('error', 'Server Error');
        } else if (error.status == 503) {
          this.toast.createToster('error', 'Server Unavailable');
        } else this.toast.createToster('error', 'Unknown Error');

        this.isLoading = false;
      }
    );
  }

  rejectRequest(username) {
    this.isLoading = true;
    this.handicapService.rejectRequest(username).subscribe(
      () => {
        this.toast.createToster('success', 'Successfully Rejected');
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 400) {
          this.toast.createToster('error', 'Bad Request');
        } else if (error.status == 401) {
          this.toast.createToster('error', 'Unauthorized');
        } else if (error.status == 403) {
          this.toast.createToster('error', 'Forbidden');
        } else if (error.status == 404) {
          this.toast.createToster('error', 'Not Found');
        } else if (error.status == 409) {
          this.toast.createToster('error', 'Conflict');
        } else if (error.status == 500) {
          this.toast.createToster('error', 'Server Error');
        } else if (error.status == 503) {
          this.toast.createToster('error', 'Server Unavailable');
        } else this.toast.createToster('error', 'Unknown Error');

        this.isLoading = false;
      }
    );
  }
}
