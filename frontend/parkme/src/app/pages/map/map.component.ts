import { Component, OnInit } from '@angular/core';
import {
  Content,
  divIcon,
  icon,
  latLng,
  Layer,
  marker,
  Popup,
  TileLayer,
  tileLayer,
} from 'leaflet';
import { ParkingLotServiceService } from 'src/app/services/parking-lot-service.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css'],
})
export class MapComponent implements OnInit {
  // Define our base layers so we can reference them multiple times
  streetMaps = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    detectRetina: true,
    maxNativeZoom: 18,
    maxZoom: 100,
  });
  wMaps = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {
    detectRetina: true,
    maxNativeZoom: 18,
    maxZoom: 100,
  });

  // Layers control object with our two base layers and the three overlay layers
  layersControl = {
    baseLayers: {
      'Street Maps': this.streetMaps,
      'Wikimedia Maps': this.wMaps,
    },
  };

  // Set the initial set of displayed layers (we could also use the leafletLayers input binding for this)
  options = {
    layers: [
      this.streetMaps,
      this.wMaps,
      /* this.superconti,
      this.lodovici,
      this.eurospin,
      this.contram,
      this.handicapFree,
      this.handicapOccupied,
      this.paymentFree,
      this.paymentOccupied,
      this.freeFree,
      this.freeOccupied */
    ],
    zoom: 16,
    center: latLng([43.14367132147207, 13.067582838096936]),
  };

  //set options for popup
  popOptions = {
    closeButton: false,
    className: 'leafletPopUp',
  };

  streetsLayers = [];

  constructor(private parkingService: ParkingLotServiceService) {}

  ngOnInit(): void {
    this.parkingService.driverGetStreetList().subscribe(
      (success) => {
        console.log(success);
        success.forEach(
          (item: {
            coordinates: { latitude: number; longitude: number };
            street: string | ((layer: Layer) => Content) | HTMLElement | Popup;
          }) => {
            let markerItem = marker(
              [item.coordinates.latitude, item.coordinates.longitude],
              {
                icon: icon({
                  iconSize: [25, 41],
                  iconAnchor: [13, 41],
                  iconUrl: 'leaflet/marker-icon.png',
                  iconRetinaUrl: 'leaflet/marker-icon-2x.png',
                  shadowUrl: 'leaflet/marker-shadow.png',
                }),
              }
            ).bindPopup(item.street, this.popOptions);
            this.streetsLayers.push(markerItem);
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
