import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ParkInfoComponent } from '../park-info/park-info.component';

@Component({
  selector: 'app-vigilant-check-park',
  templateUrl: './vigilant-check-park.component.html',
  styleUrls: ['./vigilant-check-park.component.css'],
})
export class VigilantCheckParkComponent implements OnInit {
  @Input() notification: any;

  constructor(public activeModal: NgbActiveModal, private modalService: NgbModal) {}


  ngOnInit(): void {
    console.log(this.notification);
  }

  openModalVigilantCheckInfo(info){
    const modalRef = this.modalService.open(ParkInfoComponent);
    modalRef.componentInstance.INFO = info;
    modalRef.result.then(
      () => {
        console.log('Modal Request Closed');
        this.activeModal.close();
      },
      () => {
        console.log('Modal Request Closed');
        this.activeModal.close();
      }
    );
  }
}
