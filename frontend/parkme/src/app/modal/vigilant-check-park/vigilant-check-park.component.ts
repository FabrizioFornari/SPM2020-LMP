import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-vigilant-check-park',
  templateUrl: './vigilant-check-park.component.html',
  styleUrls: ['./vigilant-check-park.component.css'],
})
export class VigilantCheckParkComponent implements OnInit {
  @Input() notification: any;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {}
}
