import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { TicketService } from 'src/app/services/driverServices/ticket.service';

@Component({
  selector: 'app-ticket-history',
  templateUrl: './ticket-history.component.html',
  styleUrls: ['./ticket-history.component.css'],
})
export class TicketHistoryComponent implements OnInit {
  ticketHistory = [];

  constructor(private titleService: Title, private ticket: TicketService) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Ticket History');
    this.getTicketHistory();
  }

  getTicketHistory() {
    this.ticketHistory = [];
    this.ticket.driverGetTicketHistory().subscribe(
      (success) => {
        console.log(success);
        success.forEach((element) => {
          let readable_date = `${new Date(
            element.expiringTimestamp
          ).toLocaleDateString('it-IT')} (${new Date(
            element.expiringTimestamp
          ).toLocaleTimeString('it-IT')})`;
          if (element.expiringTimestamp > Date.now()) {
            console.log(element);
          } else {
            element.expiringTimestamp = readable_date;
            this.ticketHistory.push(element);
          }
        });
      },
      (error) => {
        console.log(error);
        this.ticketHistory = [];
      }
    );
  }
}
