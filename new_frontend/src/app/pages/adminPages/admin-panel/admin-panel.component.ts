import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RegisterManagerComponent } from 'src/app/modals/adminModal/register-manager/register-manager.component';
import { RegisterVigilantComponent } from 'src/app/modals/adminModal/register-vigilant/register-vigilant.component';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css'],
})
export class AdminPanelComponent implements OnInit {
  constructor(
    private modalService: NgbModal,
    private router: Router,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Admin');
  }

  openModalRegisterVigilant() {
    const modalRef = this.modalService.open(RegisterVigilantComponent);
    modalRef.result.then(
      () => {
        console.log('Modal RegisterVigilant Closed');
      },
      () => {
        console.log('Modal RegisterVigilant Dismissed');
      }
    );
  }

  openModalRegisterManager() {
    const modalRef = this.modalService.open(RegisterManagerComponent);
    modalRef.result.then(
      () => {
        console.log('Modal RegisterManager Closed');
      },
      () => {
        console.log('Modal RegisterManager Dismissed');
      }
    );
  }

  handicapRequestList() {
    this.router.navigate(['admin-panel/handicap-requests']);
  }
}
