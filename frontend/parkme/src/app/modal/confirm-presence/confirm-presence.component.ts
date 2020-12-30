import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
@Component({
  selector: 'app-confirm-presence',
  templateUrl: './confirm-presence.component.html',
  styleUrls: ['./confirm-presence.component.css'],
})
export class ConfirmPresenceComponent implements OnInit {
  @Input() notification: string;

  constructor(
    public activeModal: NgbActiveModal
  ) {}

  ngOnInit(): void {}

  assert(){
    console.log('sono io')
    this.activeModal.close();
  }

  deny(){
    console.log('non sono io')
    this.activeModal.close();
  }
}
