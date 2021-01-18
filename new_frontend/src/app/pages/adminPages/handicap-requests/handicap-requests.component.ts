import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HandicapRequestComponent } from 'src/app/modals/adminModal/handicap-request/handicap-request.component';
import { HandicapRequestService } from 'src/app/services/adminServices/handicap-request.service';
import { NgxToastService } from 'src/app/services/commonServices/ngx-toast.service';

@Component({
  selector: 'app-handicap-requests',
  templateUrl: './handicap-requests.component.html',
  styleUrls: ['./handicap-requests.component.css'],
})
export class HandicapRequestsComponent implements OnInit {
  show: boolean = false;
  requests = [];

  constructor(
    private titleService: Title,
    private handicapService: HandicapRequestService,
    private modalService: NgbModal,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Handicap Requests');
    this.getRequest();
  }

  getRequest() {
    this.handicapService.downloadRequest().subscribe(
      (success) => {
        success.forEach((element) => {
          element.timestamp = `${new Date(element.timestamp).toLocaleDateString(
            'it-IT'
          )} (${new Date(element.timestamp).toLocaleTimeString('it-IT')})`;
        });
        this.requests = success;
        this.show = true;
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
        this.requests = [];
        this.show = false;
      }
    );
  }

  openModalHandicapRequest(req) {
    const modalRef = this.modalService.open(HandicapRequestComponent);
    modalRef.componentInstance.REQ = req;
    modalRef.result.then(
      () => {
        console.log('Modal HandicapRequest Closed');
        this.getRequest();
      },
      () => {
        console.log('Modal HandicapRequest Dismissed');
        this.getRequest();
      }
    );
  }
}
