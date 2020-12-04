import { Component, OnInit, NgZone } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {
  Content,
  divIcon,
  icon,
  latLng,
  Layer,
  marker,
  Popup,
  tileLayer,
} from 'leaflet';
import { ConfirmParkingLotComponent } from 'src/app/modal/confirm-parking-lot/confirm-parking-lot.component';
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
    layers: [this.streetMaps],
    zoom: 16,
    center: latLng([43.14367132147207, 13.067582838096936]),
  };

  streetsLayers = [];

  automatic: boolean = false;

  constructor(
    private parkingService: ParkingLotServiceService,
    private zone: NgZone,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    if (history.state.automatic != null) {
      this.automatic = history.state.automatic;
    }

    this.streetsLayers = [];
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
            )
              .bindTooltip(item.street.toString())
              .on('click', () =>
                this.automatic
                  ? this.automaticSearch(item.coordinates.latitude, item.coordinates.longitude)
                  : this.getParks(item.street)
              );
            this.streetsLayers.push(markerItem);
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
  }

  automaticSearchFromClick(e){
    if (this.automatic) {
      this.automaticSearch(e.latlng.lat, e.latlng.lng);
    } else return null;
  }

  automaticSearch(latitude, longitude) {
    alert(`${latitude} / ${longitude}`);
  }


  getParks(street: string | ((layer: Layer) => Content) | HTMLElement | Popup) {
    this.parkingService.driverGetParkingLots(street).subscribe(
      (success) => {
        console.log(success);
        let markers = [];
        success.forEach((item) => {
          if (item.status !== 'DISABLED') {
            let markerItem = this.createMarker(item).on('click', () =>
              this.zone.run(() => this.openModalBookParking(item))
            );
            markers.push(markerItem);
          }
        });
        this.zone.run(() => (this.streetsLayers = markers));
      },
      (error) => {
        console.log(error);
      }
    );
  }

  createMarker(item) {
    let html = '';
    if (
      !item.isHandicapParkingLot &&
      item.status == 'FREE' &&
      item.pricePerHours == 0
    ) {
      html =
        "<div class='marker-pin-free'></div><i class='material-icons'>stop_circle</i>";
    } else if (
      !item.isHandicapParkingLot &&
      item.status == 'BOOKED' &&
      item.pricePerHours == 0
    ) {
      html =
        "<div class='marker-pin-occupied'></div><i class='material-icons'>stop_circle</i>";
    } else if (
      !item.isHandicapParkingLot &&
      item.status == 'FREE' &&
      item.pricePerHours > 0
    ) {
      html =
        "<div class='marker-pin-free'></div><i class='material-icons'>monetization_on</i>";
    } else if (
      !item.isHandicapParkingLot &&
      item.status == 'BOOKED' &&
      item.pricePerHours > 0
    ) {
      html =
        "<div class='marker-pin-occupied'></div><i class='material-icons'>monetization_on</i>";
    } else if (item.isHandicapParkingLot && item.status == 'FREE') {
      html =
        "<div class='marker-pin-free'></div><i class='material-icons'>accessible_forward</i>";
    } else if (item.isHandicapParkingLot && item.status == 'BOOKED') {
      html =
        "<div class='marker-pin-occupied'></div><i class='material-icons'>accessible_forward</i>";
    } else {
      console.table(item);
    }

    return marker([item.coordinates.latitude, item.coordinates.longitude], {
      icon: divIcon({
        className: 'custom-div-icon',
        html: html,
        iconSize: [30, 42],
        iconAnchor: [15, 42],
      }),
    }).bindTooltip(item.numberOfParkingLot.toString());
  }

  openModalBookParking(street: any) {
    let modalRef = this.modalService.open(ConfirmParkingLotComponent);
    modalRef.componentInstance.PARKINGLOT = street;
    modalRef.result.then(
      () => {
        console.log('Modal Confirm Parking Lot Closed');
        this.ngOnInit();
      },
      () => {
        console.log('Modal Confirm Parking Lot Dismissed');
      }
    );
  }
}
