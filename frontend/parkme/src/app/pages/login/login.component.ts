import { Component, OnInit } from '@angular/core';
import { UnifiedLoginService } from 'src/app/services/unified-login.service';
import { ToastrService } from "ngx-toastr";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email: string;
  password: string;
  isLoading: boolean = false;
  constructor(private unifiedLogin: UnifiedLoginService, private toastrService: ToastrService) {}

  ngOnInit(): void {}

  onLoginSubmit() {
    this.isLoading = true;

    const user = {
      email: this.email,
      password: this.password,
    };

    this.unifiedLogin.login(user).subscribe(
      (data) => {
        localStorage.setItem('token', data.token);
        localStorage.setItem('email', data.email);
        localStorage.setItem('role', data.roles[0]);
        this.toastrService.success('Successfully Logged In');
        this.isLoading = false;
      },
      (error) => {
        if (error.status == 401) {
          this.toastrService.warning('Bad Credentials');
        }
        this.isLoading = false;
      }
    );
  }
}
