import { Component, Input, OnInit } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-buy-subscription",
  templateUrl: "./buy-subscription.component.html",
  styleUrls: ["./buy-subscription.component.css"],
})
export class BuySubscriptionComponent implements OnInit {
  @Input() PARKINGLOT;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {
    console.table(this.PARKINGLOT);
  }
}
