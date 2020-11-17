import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdateEmailComponent } from 'src/app/modal/update-email/update-email.component';
import { UpdatePasswordComponent } from 'src/app/modal/update-password/update-password.component';
import { UpdatePhoneComponent } from 'src/app/modal/update-phone/update-phone.component';
import { UpdateVehiclePlateComponent } from 'src/app/modal/update-vehicle-plate/update-vehicle-plate.component';
@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }



  openModalUpdateEmail() {
    const modalRef = this.modalService.open(UpdateEmailComponent);
    modalRef.componentInstance.EMAIL = "mario@park.it";
    modalRef.result.then(
      () => {
        console.log('Modal Update Email Closed');
      },
      () => {
        console.log('Modal Update Email Closed');
      }
    );
  }

  openModalUpdatePhone() {
    const modalRef = this.modalService.open(UpdatePhoneComponent);
    modalRef.componentInstance.PHONE = "(+39) 3741793372";
    modalRef.result.then(
      () => {
        console.log('Modal Update Phone Closed');
      },
      () => {
        console.log('Modal Update Phone Closed');
      }
    );
  }


  openModalUpdatePassword() {
    const modalRef = this.modalService.open(UpdatePasswordComponent);
    modalRef.result.then(
      () => {
        console.log('Modal Update Password Closed');
      },
      () => {
        console.log('Modal Update Password Closed');
      }
    );
  }


  openModalUpdatePlateVehicle() {
    const modalRef = this.modalService.open(UpdateVehiclePlateComponent);
    modalRef.componentInstance.PLATE = "FA345BE";
    modalRef.componentInstance.VEHICLE = "4 Wheels Standard Vehicle";
    modalRef.result.then(
      () => {
        console.log('Modal Update Plate/Vehicle Closed');
      },
      () => {
        console.log('Modal Update Plate/Vehicle Closed');
      }
    );
  }

}
