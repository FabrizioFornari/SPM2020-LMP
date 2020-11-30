import { Component, OnInit } from '@angular/core';
import { icon, latLng, Map, marker, point, polyline, tileLayer } from 'leaflet';

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

  // Marker for the parking lot at Superconti
  superconti = marker([43.14382255580331, 13.069720441965472], {
    icon: icon({
      iconSize: [25, 41],
      iconAnchor: [13, 41],
      iconUrl: 'leaflet/marker-icon.png',
      iconRetinaUrl: 'leaflet/marker-icon-2x.png',
      shadowUrl: 'leaflet/marker-shadow.png',
    }),
  });

  // Marker for the parking lot at Polo Lodovici
  lodovici = marker([43.139719268413266, 13.068769368949196], {
    icon: icon({
      iconSize: [25, 41],
      iconAnchor: [13, 41],
      iconUrl: 'leaflet/marker-icon.png',
      iconRetinaUrl: 'leaflet/marker-icon-2x.png',
      shadowUrl: 'leaflet/marker-shadow.png',
    }),
  });

  // Marker for the parking lot at Eurospin
  eurospin = marker([43.144892512182224, 13.067108097785395], {
    icon: icon({
      iconSize: [25, 41],
      iconAnchor: [13, 41],
      iconUrl: 'leaflet/marker-icon.png',
      iconRetinaUrl: 'leaflet/marker-icon-2x.png',
      shadowUrl: 'leaflet/marker-shadow.png',
    }),
  });

  // Marker for the parking lot at Contram
  contram = marker([43.142463059981274, 13.077146597785206], {
    icon: icon({
      iconSize: [25, 41],
      iconAnchor: [13, 41],
      iconUrl: 'leaflet/marker-icon.png',
      iconRetinaUrl: 'leaflet/marker-icon-2x.png',
      shadowUrl: 'leaflet/marker-shadow.png',
    }),
  });

  // Layers control object with our two base layers and the three overlay layers
  layersControl = {
    baseLayers: {
      'Street Maps': this.streetMaps,
      'Wikimedia Maps': this.wMaps,
    },
    overlays: {
      Superconti: this.superconti,
      'Polo Lodovici': this.lodovici,
      Eurospin: this.eurospin,
      Contram: this.contram,
    },
  };

  // Set the initial set of displayed layers (we could also use the leafletLayers input binding for this)
  options = {
    layers: [
      this.streetMaps,
      this.superconti,
      this.lodovici,
      this.eurospin,
      this.contram,
    ],
    zoom: 16,
    center: latLng([43.14367132147207, 13.067582838096936]),
  };

  //set options for popup
  popOptions = {
    closeButton: false,
    className: 'leafletPopUp',
  };

  constructor() {}

  ngOnInit(): void {
    this.superconti.bindPopup('Superconti', this.popOptions);
    this.eurospin.bindPopup('Eurospin', this.popOptions);
    this.lodovici.bindPopup('Polo Lodovici', this.popOptions);
    this.contram.bindPopup('Contram', this.popOptions);
  }
}
