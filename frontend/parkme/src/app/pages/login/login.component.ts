import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string;
  password: string;
  isLoading: boolean = false;
  constructor() {}

  ngOnInit(): void {}

  onLoginSubmit() {
    this.isLoading = true;

    const user = {
      email: this.email,
      password: this.password,
    };

    console.table(user);
    this.isLoading = false;
  }
}
