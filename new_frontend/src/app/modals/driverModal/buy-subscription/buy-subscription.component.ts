import { Component, Input, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Router } from "@angular/router";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";
import { NgxToastService } from "src/app/services/commonServices/ngx-toast.service";
import { TicketService } from "src/app/services/driverServices/ticket.service";

@Component({
  selector: "app-buy-subscription",
  templateUrl: "./buy-subscription.component.html",
  styleUrls: ["./buy-subscription.component.css"],
})
export class BuySubscriptionComponent implements OnInit {
  @Input() PARKINGLOT;

  monthsList: Array<String> = ["1", "2", "3", "4", "6", "12"];
  months: string = null;

  userInfo;

  constructor(
    public activeModal: NgbActiveModal,
    private ticketService: TicketService,
    private toast: NgxToastService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userInfo = JSON.parse(localStorage.getItem("user"));
  }

  backButton() {
    this.months = null;
    this.activeModal.dismiss();
  }

  buySubscription(form: NgForm) {
    let body = {
      personalParkingLot: this.PARKINGLOT,
      months: parseInt(form.value.months),
    };
    this.ticketService.driverBuySubscription(body).subscribe(
      (success) => {
        console.log(success);
        this.toast.createToster("success", "Subscription Bought");
        this.months = null;
        this.activeModal.dismiss();
        this.router.navigate(["/buy-ticket"]);
      },
      (error) => {
        console.log(error);
        this.toast.createToster("error", "Error Buying Subscription");
        this.months = null;
        this.activeModal.dismiss();
      }
    );
  }
}
