import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  form = {
    email: "",
    password: ""
  }

  constructor() { }

  ngOnInit() {
  }

  logForm() {
    console.table(this.form)
  }

}
