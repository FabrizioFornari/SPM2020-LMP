import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vigilant-panel',
  templateUrl: './vigilant-panel.component.html',
  styleUrls: ['./vigilant-panel.component.css'],
})
export class VigilantPanelComponent implements OnInit {
  constructor(private titleService: Title, private router: Router) {}

  ngOnInit(): void {
    this.titleService.setTitle('ParkMe | Vigilant');
  }

  parkingLotsList() {
    this.router.navigate(['vigilant-panel/parking-lot-list']);
  }
}
