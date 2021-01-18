import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root',
})
export class NgxToastService {
  successOption = {
    preventDuplicates: true,
    positionClass: 'toast-top-right',
    timeOut: 3000,
  };

  infoOption = {
    preventDuplicates: true,
    positionClass: 'toast-top-right',
    timeOut: 3000,
  };

  warningOption = {
    closeButton: true,
    positionClass: 'toast-top-right',
    preventDuplicates: true,
    disableTimeOut: true,
  };

  errorOption = {
    closeButton: true,
    positionClass: 'toast-top-right',
    preventDuplicates: true,
    disableTimeOut: true,
  };

  constructor(private toastr: ToastrService) {}

  createToster(type: string, message: string) {
    if (type == 'success') {
      this.toastr.success(message, null, this.successOption);
    } else if (type == 'info') {
      this.toastr.info(message, null, this.infoOption);
    } else if (type == 'warning') {
      this.toastr.warning(message, null, this.warningOption);
    } else if (type == 'error') {
      this.toastr.error(message, null, this.warningOption);
    }
  }
}
