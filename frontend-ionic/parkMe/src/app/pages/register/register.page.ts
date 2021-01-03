import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  form = {
    firstName: "",
    lastName: "",
    ssn: "",
    email: "",
    phone: "",
    plate: "",
    type: "",
    password: ""
  }

  constructor() { }

  ngOnInit() {
  }

  logForm() {
    console.table(this.form)
  }

}
