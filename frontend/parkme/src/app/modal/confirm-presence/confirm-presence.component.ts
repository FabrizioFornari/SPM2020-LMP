import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-confirm-presence',
  templateUrl: './confirm-presence.component.html',
  styleUrls: ['./confirm-presence.component.css'],
})
export class ConfirmPresenceComponent implements OnInit {
  @Input() notification: any;

  constructor(
    public activeModal: NgbActiveModal,
    private router: Router,
    private toastrService: ToastrService
  ) {}

  ngOnInit(): void {}

  assert(){
    this.toastrService.warning('Please Confirm Your Arrival');
    this.router.navigate(['/ticket-list']);
    this.activeModal.close();
  }

  deny(){
    console.log('non sono io')
    this.activeModal.close();
  }
}
