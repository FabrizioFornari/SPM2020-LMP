import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload-handicap',
  templateUrl: './upload-handicap.component.html',
  styleUrls: ['./upload-handicap.component.css'],
})
export class UploadHandicapComponent implements OnInit {
  userInfo = {
    firstName: "",
    lastName: "",
    ssn: "",
    email: "",
    phone: "",
    plate: ""
  };

  isLoading: boolean = false;

  constructor() {
    this.userInfo = JSON.parse(localStorage.getItem("user"));
  }

  ngOnInit(): void {}

  onSubmit() {
    alert(JSON.stringify(this.userInfo));
  }
}
