import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-park-info',
  templateUrl: './park-info.component.html',
  styleUrls: ['./park-info.component.css']
})
export class ParkInfoComponent implements OnInit {

  @Input() INFO: any;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

}
