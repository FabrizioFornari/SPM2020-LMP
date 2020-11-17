import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdateEmailComponent } from 'src/app/modal/update-email/update-email.component';
@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }


  changePassword(){
    console.log('Change Password');
  }

  changePhone(){
    console.log('Change Phone');
  }

  changeVehicle_Plate(){
    console.log('Change Vehicle/Plate');
  }


  openModalUpdateEmail() {
    const modalRef = this.modalService.open(UpdateEmailComponent);
    modalRef.result.then(
      () => {
        console.log('Modal Update Email Closed');
      },
      () => {
        console.log('Modal Update Email Closed');
      }
    );
  }

}
