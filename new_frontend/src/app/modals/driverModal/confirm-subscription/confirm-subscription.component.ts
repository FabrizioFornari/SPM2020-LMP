import { Component, Input, OnInit } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";
import { NgxToastService } from "src/app/services/commonServices/ngx-toast.service";
import { TicketService } from "src/app/services/driverServices/ticket.service";

@Component({
  selector: "app-confirm-subscription",
  templateUrl: "./confirm-subscription.component.html",
  styleUrls: ["./confirm-subscription.component.css"],
})
export class ConfirmSubscriptionComponent implements OnInit {
  @Input() CURRENT_SUBSCRIPTION: any;

  constructor(
    public activeModal: NgbActiveModal,
    private ticketService: TicketService,
    private toast: NgxToastService
  ) {}

  ngOnInit(): void {}

  confirmSubScription() {
    this.ticketService.driverConfirmSubscription().subscribe(
      (success) => {
        console.log(success);
        this.toast.createToster("success", "Presence Confirmed");
        this.activeModal.dismiss();
      },
      (error) => {
        console.log(error);
        this.toast.createToster("error", "Error during presence confirmation");
      }
    );
  }

  deny() {
    this.activeModal.dismiss();
    this.toast.createToster(
      "info",
      "A Vigilant will check who is occupying your personal parking lot."
    );
  }
}
