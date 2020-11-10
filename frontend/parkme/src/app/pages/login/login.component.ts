import { Component, OnInit } from '@angular/core';
import { UnifiedLoginService } from 'src/app/services/unified-login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string;
  password: string;
  isLoading: boolean = false;
  constructor(private unifiedLogin: UnifiedLoginService) {}

  ngOnInit(): void {}

  onLoginSubmit() {
    this.isLoading = true;

    const user = {
      email: this.email,
      password: this.password,
    };

    this.unifiedLogin.login(user).subscribe(
      (data) => {
        console.log(data);
        this.isLoading = false;
      },
      (error) => {
        console.log(error);
        this.isLoading = false;
      }
    );
  }
}
