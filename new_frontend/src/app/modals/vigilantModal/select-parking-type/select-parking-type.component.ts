import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-select-parking-type",
  templateUrl: "./select-parking-type.component.html",
  styleUrls: ["./select-parking-type.component.css"],
})
export class SelectParkingTypeComponent implements OnInit {
  @Output() passEntry: EventEmitter<any> = new EventEmitter();

  constructor(public activeModal: NgbActiveModal) {}

  ngOnInit(): void {}

  public() {
    this.passEntry.emit("public");
    this.activeModal.dismiss();
  }
  personal() {
    this.passEntry.emit("personal");
    this.activeModal.dismiss();
  }
}
