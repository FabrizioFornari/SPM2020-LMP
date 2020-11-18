import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload-handicap',
  templateUrl: './upload-handicap.component.html',
  styleUrls: ['./upload-handicap.component.css']
})
export class UploadHandicapComponent implements OnInit {

  firstname: string = "Andrea";
  lastname: string = "Falaschini";
  ssn: string = "RSSMRA80A01F205X"
  email: string = "flash@park.it";
  phone: string = "3473917227";
  plate: string = "ES943VB";
  

  isLoading: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }


  onSubmit(){
    const body = {
      firstname: this.firstname,
      lastname: this.lastname,
      ssn: this.ssn,
      email: this.email,
      phone: this.phone,
      plate: this.plate
    }
    alert(JSON.stringify(body));
  }

}
