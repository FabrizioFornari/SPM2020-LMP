import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  newBooking(){
    alert('NEW BOOKING');
  }

  ticketClick(){
    alert("Row Clicked");
  }

  confirm(){
    alert("Confirmed");
  }

  maps(){
    alert("Maps");
  }

  cancel(){
    alert("Cancelled");
  }

}
