import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { HandleHandicapRequestsService } from 'src/app/services/handle-handicap-requests.service';

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
    private toastrService: ToastrService,
    private handleReq: HandleHandicapRequestsService
  ) {}

  ngOnInit(): void {}

  acceptRequest(req: { username: any; }){
    this.isLoading = true;
    this.handleReq.acceptReq(req).subscribe(
      () => {
        this.toastrService.success(`${req.username} accepted`);
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 208) {
          this.toastrService.warning('You have been already accepted');
        } else if ((error.status = 409)) {
          this.toastrService.warning('You have a pending request');
        } else if ((error.status = 500)) {
          this.toastrService.warning('Server Error');
        } else {
          this.toastrService.warning('Unknown Error');
        }
        this.isLoading = false;
        this.activeModal.dismiss();
      }
    );
  }

  declineRequest(req: { username: any; }){
    this.isLoading = true;
    this.handleReq.declineReq(req).subscribe(
      () => {
        this.toastrService.warning(`${req.username} declined`);
        this.isLoading = false;
        this.activeModal.dismiss();
      },
      (error) => {
        if (error.status == 208) {
          this.toastrService.warning('You have been already accepted');
        } else if ((error.status = 409)) {
          this.toastrService.warning('You have a pending request');
        } else if ((error.status = 500)) {
          this.toastrService.warning('Server Error');
        } else {
          this.toastrService.warning('Unknown Error');
        }
        this.isLoading = false;
        this.activeModal.dismiss();
      }
    );
  }
}
