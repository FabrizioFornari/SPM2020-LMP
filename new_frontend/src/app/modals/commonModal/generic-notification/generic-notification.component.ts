import { Component, Input, OnInit } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-generic-notification",
  templateUrl: "./generic-notification.component.html",
  styleUrls: ["./generic-notification.component.css"],
})
export class GenericNotificationComponent implements OnInit {
  @Input() notification;

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {}
}
