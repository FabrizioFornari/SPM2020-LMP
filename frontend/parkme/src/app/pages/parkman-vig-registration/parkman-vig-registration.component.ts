import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-parkman-vig-registration',
  templateUrl: './parkman-vig-registration.component.html',
  styleUrls: ['./parkman-vig-registration.component.css']
})
export class ParkmanVigRegistrationComponent implements OnInit {

  accountType: Array<String> = [
    'Vigilant',
    'Parking Manager'
  ];
  account: string;
  firstName: string;
  lastName: string;
  ssn: string;
  email: string;
  phone: string
  password: string;
  

  accountError: boolean = false;
  firstNameError: boolean = false;
  lastNameError: boolean = false;
  ssnError: boolean = false;
  emailError: boolean = false;
  phoneError: boolean = false;
  passwordError: boolean = false;

  isLoading: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  onRegisterSubmit(){

    let accountType = this.account;

    const user = {
      
      firstName : this.firstName,
      lastName: this.lastName,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      password: this.password
    }
    console.log(`Account Type: ${accountType}`);
    console.table(user);
  }

}
