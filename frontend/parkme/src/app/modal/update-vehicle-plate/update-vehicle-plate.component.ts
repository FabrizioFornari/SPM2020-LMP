import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-update-vehicle-plate',
  templateUrl: './update-vehicle-plate.component.html',
  styleUrls: ['./update-vehicle-plate.component.css']
})
export class UpdateVehiclePlateComponent implements OnInit {

  @Input() PLATE: string;
  @Input() VEHICLE: string;
  newPlate: string;
  vehicleType: Array<String> = [
    '2 Wheels Vehicle',
    '4 Wheels Standard Vehicle',
    '4 Wheels Big Vehicle',
  ];
  newVehicle: string;

  constructor(public activeModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  updatePlateVehicle(form){
    console.log(`New Plate: ${form.value.newPlate}`);
    console.log(`New Vehicle Type: ${form.value.newVehicle}`);
    this.activeModal.dismiss();
  }

}
