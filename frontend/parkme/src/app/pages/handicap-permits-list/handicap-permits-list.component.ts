import { Component, OnInit } from '@angular/core';

import { FormControl } from '@angular/forms';

import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { HandicapRequestDownloadService } from 'src/app/services/handicap-request-download.service';

import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OpenHandicapRequestComponent } from 'src/app/modal/open-handicap-request/open-handicap-request.component';

interface Permit {
  username: string;
  timestamp: string;
  accepted: boolean;
  processed: boolean;
}

@Component({
  selector: 'app-handicap-permits-list',
  templateUrl: './handicap-permits-list.component.html',
  styleUrls: ['./handicap-permits-list.component.css'],
})
export class HandicapPermitsListComponent implements OnInit {

  show: boolean = false;

  PERMITS: Permit[] = [];

  permits$: Observable<Permit[]>;
  filter = new FormControl('');

  constructor(
    private reqDown: HandicapRequestDownloadService,
    private modalService: NgbModal
  ) {
    this.permits$ = this.filter.valueChanges.pipe(
      startWith(''),
      map((text) => this.search(text))
    );
  }

  ngOnInit(): void {
    this.updateEntry();
  }

  updateEntry(){
    this.show = false;
    this.reqDown.downloadRequest().subscribe(
      (data) => {
        data.forEach(element => {
          element.timestamp = `${new Date(element.timestamp).toLocaleDateString("it-IT")} (${new Date(element.timestamp).toLocaleTimeString("it-IT")})`;
        });
        this.PERMITS = data;
        this.show = true;
        console.table(data);
      },
      (error) => {
        console.log(error);
      }
    );
  }

  search(text: string): Permit[] {
    return this.PERMITS.filter((permit) => {
      const term = text.toLowerCase();
      return (
        permit.username.toLowerCase().includes(term) ||
        permit.timestamp.toString().toLowerCase().includes(term)
      );
    });
  }

  openRequest(permit: any){
    const modalRef = this.modalService.open(OpenHandicapRequestComponent);
    modalRef.componentInstance.REQUEST = permit;
    modalRef.result.then(
      () => {
        console.log('Modal Request Closed');
        this.updateEntry();
      },
      () => {
        console.log('Modal Request Closed');
        this.updateEntry();
      }
    );
  }
}
