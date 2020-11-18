import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { UploadHandicapServiceService } from 'src/app/services/upload-handicap-service.service';

@Component({
  selector: 'app-upload-handicap',
  templateUrl: './upload-handicap.component.html',
  styleUrls: ['./upload-handicap.component.css'],
})
export class UploadHandicapComponent implements OnInit {
  userInfo = {
    firstName: '',
    lastName: '',
    ssn: '',
    email: '',
    phone: '',
    plate: '',
  };

  isLoading: boolean = false;

  constructor(
    private uploadHandicap: UploadHandicapServiceService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.userInfo = JSON.parse(localStorage.getItem('user'));
  }

  onSubmit() {
    this.isLoading = true;
    this.uploadHandicap.uploadRequest().subscribe(
      () => {
        this.toastr.success('Request Successfully Uploaded');
        this.isLoading = false;
      },
      (error) => {
        if (error.status == 208) {
          this.toastr.warning('You have been already accepted');
        } else if ((error.status = 409)) {
          this.toastr.warning('You have a pending request');
        } else if ((error.status = 500)) {
          this.toastr.warning('Server Error');
        } else {
          this.toastr.warning('Unknown Error');
        }
        this.isLoading = false;
      }
    );
  }
}
