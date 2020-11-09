import { Component, OnInit } from '@angular/core';
import { DriverAuthService } from 'src/app/services/driver-auth.service';

@Component({
  selector: 'app-driver-registration',
  templateUrl: './driver-registration.component.html',
  styleUrls: ['./driver-registration.component.css'],
})
export class DriverRegistrationComponent implements OnInit {
  name: string;
  surname: string;
  ssn: string;
  email: string;
  phone: number;
  plate: string;
  vehicleType: Array<String> = ["2 Wheels Vehicle", "4 Wheels Standard Vehicle", "4 Wheels Big Vehicle"];
  vehicle: string;
  password: string;
  isLoading: boolean = false;
  constructor(private driverAuthService: DriverAuthService) {}

  ngOnInit(): void {}


  onLoginSubmit() {
    this.isLoading = true;

    const user = {
      firstName: this.name,
      lastName: this.surname,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      plate: this.plate,
      vehicleType: this.vehicle,
      password: this.password,
    };

    this.driverAuthService.register(user).subscribe(
      data => {
        console.log(data)
        this.isLoading = false;
      },
      error => {
        console.log(error)
        this.isLoading = false;
      }
    );
  }

}
