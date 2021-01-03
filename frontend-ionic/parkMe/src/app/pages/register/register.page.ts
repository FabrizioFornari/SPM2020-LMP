import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

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

  constructor(private titleService: Title) {}

  ngOnInit() {}

  ionViewWillEnter(){
    this.titleService.setTitle('ParkMe | Register');
  }

  logForm() {
    console.table(this.form)
  }

}
