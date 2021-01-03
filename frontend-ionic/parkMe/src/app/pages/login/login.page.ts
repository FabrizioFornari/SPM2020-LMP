import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

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

  constructor(private titleService: Title) {}

  ngOnInit() {}

  ionViewWillEnter(){
    this.titleService.setTitle('ParkMe | Login');
  }

  logForm() {
    console.table(this.form)
  }

}
