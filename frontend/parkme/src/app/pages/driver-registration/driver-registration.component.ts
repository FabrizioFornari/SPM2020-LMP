import { Component, OnInit } from '@angular/core';

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
  constructor() {}

  ngOnInit(): void {}


  onLoginSubmit() {
    this.isLoading = true;

    const user = {
      name: this.name,
      surname: this.surname,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      plate: this.plate,
      vehicle: this.vehicle,
      password: this.password,
    };

    console.table(user);
    this.isLoading = false;
  }
}
