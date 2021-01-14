import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';
import { UploadHandicapRequestService } from 'src/app/services/driverServices/upload-handicap-request.service';

@Component({
  selector: 'app-upload-handicap-request',
  templateUrl: './upload-handicap-request.component.html',
  styleUrls: ['./upload-handicap-request.component.css'],
})
export class UploadHandicapRequestComponent implements OnInit {
  @Input() USER: any;

  isLoading: boolean = false;
  constructor(
    public activeModal: NgbActiveModal,
    private handicapUpload: UploadHandicapRequestService,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {}

  uploadRequest() {
    this.isLoading = true;
    this.handicapUpload.uploadRequest().subscribe(
      () => {
        this.toast.createToster('success', 'Successfully Uploaded');
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
          this.toast.createToster(
            'error',
            'You have already requested handicap permits'
          );
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
