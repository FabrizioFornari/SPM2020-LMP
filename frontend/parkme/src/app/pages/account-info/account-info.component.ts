import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  changeEmail(){
    console.log('Change Email');
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

}
