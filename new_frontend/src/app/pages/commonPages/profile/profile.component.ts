import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdateEmailComponent } from 'src/app/modals/driverModal/update-email/update-email.component';
import { UpdatePasswordComponent } from 'src/app/modals/driverModal/update-password/update-password.component';
import { UpdatePhoneComponent } from 'src/app/modals/driverModal/update-phone/update-phone.component';
import { UpdateVehicleComponent } from 'src/app/modals/driverModal/update-vehicle/update-vehicle.component';
import { UploadHandicapRequestComponent } from 'src/app/modals/driverModal/upload-handicap-request/upload-handicap-request.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  user = {
    firstName: '',
    lastName: '',
    roles: '',
    id: '',
    ssn: '',
    email: '',
    phone: '',
    plate: '',
    vehicleType: '',
    isHandicap: false,
  };

  constructor(private titleService: Title, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Profile');
    this.user = JSON.parse(localStorage.getItem('user'));
  }

  openModalHandicapRequest() {
    const modalRef = this.modalService.open(UploadHandicapRequestComponent);
    modalRef.componentInstance.USER = this.user;
    modalRef.result.then(
      () => {
        console.log('Modal UploadRequest Closed');
      },
      () => {
        console.log('Modal UploadRequest Dismissed');
      }
    );
  }

  openModalUpdatePlateVehicle() {
    const modalRef = this.modalService.open(UpdateVehicleComponent);
    modalRef.componentInstance.PLATE = this.user.plate;
    modalRef.componentInstance.VEHICLE = this.user.vehicleType;
    modalRef.result.then(
      () => {
        console.log('Modal Update Plate/Vehicle Closed');
      },
      () => {
        console.log('Modal Update Plate/Vehicle Dismissed');
      }
    );
  }

  openModalUpdateEmail() {
    const modalRef = this.modalService.open(UpdateEmailComponent);
    modalRef.componentInstance.EMAIL = this.user.email;
    modalRef.result.then(
      () => {
        console.log('Modal Update Email Closed');
      },
      () => {
        console.log('Modal Update Email Dismissed');
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
        console.log('Modal Update Password Dismissed');
      }
    );
  }

  openModalUpdatePhone() {
    const modalRef = this.modalService.open(UpdatePhoneComponent);
    modalRef.componentInstance.PHONE = this.user.phone;
    modalRef.result.then(
      () => {
        console.log('Modal Update Phone Closed');
      },
      () => {
        console.log('Modal Update Phone Closed');
      }
    );
  }
}
