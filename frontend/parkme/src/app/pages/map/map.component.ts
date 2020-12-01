import { Component, OnInit } from '@angular/core';
import { divIcon, icon, latLng, marker, tileLayer } from 'leaflet';

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

  // Marker for the parking lot at Eurospin (for handicap, free)
  handicapFree = marker([43.14509139916818, 13.06653403554103], {
    icon: divIcon({
      className: 'custom-div-icon',
        html: "<div class='marker-pin-free'></div><i class='material-icons'>accessible_forward</i>",
        iconSize: [30, 42],
        iconAnchor: [15, 42]
    }),
  });

  // Marker for the parking lot at Eurospin (for handicap, occupied)
  handicapOccupied = marker([43.1450620992383, 13.067087786987548], {
    icon: divIcon({
      className: 'custom-div-icon',
        html: "<div class='marker-pin-occupied'></div><i class='material-icons'>accessible_forward</i>",
        iconSize: [30, 42],
        iconAnchor: [15, 42]
    }),
  });

  // Marker for the parking lot at Eurospin (payment, free)
  paymentFree = marker([43.14488784147042, 13.067358322045388], {
    icon: divIcon({
      className: 'custom-div-icon',
        html: "<div class='marker-pin-free'></div><i class='material-icons'>monetization_on</i>",
        iconSize: [30, 42],
        iconAnchor: [15, 42]
    }),
  });

  // Marker for the parking lot at Eurospin (payment, occupied)
  paymentOccupied = marker([43.14470587265168, 13.067335072938858], {
    icon: divIcon({
      className: 'custom-div-icon',
        html: "<div class='marker-pin-occupied'></div><i class='material-icons'>monetization_on</i>",
        iconSize: [30, 42],
        iconAnchor: [15, 42]
    }),
  });

  // Marker for the parking lot at Eurospin (free, free)
  freeFree = marker([43.14463339339508, 13.066867977253052], {
    icon: divIcon({
      className: 'custom-div-icon',
        html: "<div class='marker-pin-free'></div><i class='material-icons'>stop_circle</i>",
        iconSize: [30, 42],
        iconAnchor: [15, 42]
    }),
  });

  // Marker for the parking lot at Eurospin (free, occupied)
  freeOccupied = marker([43.14478760447626, 13.066692552176484], {
    icon: divIcon({
      className: 'custom-div-icon',
        html: "<div class='marker-pin-occupied'></div><i class='material-icons'>stop_circle</i>",
        iconSize: [30, 42],
        iconAnchor: [15, 42]
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
      this.handicapFree,
      this.handicapOccupied,
      this.paymentFree,
      this.paymentOccupied,
      this.freeFree,
      this.freeOccupied
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
    this.freeOccupied.bindPopup('Occupied, No Cash', this.popOptions);
    this.freeFree.bindPopup('Free, No Cash', this.popOptions);
    this.handicapOccupied.bindPopup('Occupied, Handicap Only', this.popOptions);
    this.handicapFree.bindPopup('Free, Handicap Only', this.popOptions);
    this.paymentOccupied.bindPopup('Occupied, Payment', this.popOptions);
    this.paymentFree.bindPopup('Free, Payment', this.popOptions);
  }
}
