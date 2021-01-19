import { Component, OnInit, NgZone } from "@angular/core";
import { Title } from "@angular/platform-browser";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import {
  Content,
  divIcon,
  icon,
  latLng,
  Layer,
  marker,
  Popup,
  tileLayer,
} from "leaflet";
import { ConfirmParkingLotBookingComponent } from "src/app/modals/driverModal/confirm-parking-lot-booking/confirm-parking-lot-booking.component";
import { CoordinatesModalComponent } from "src/app/modals/driverModal/coordinates-modal/coordinates-modal.component";
import { TicketService } from "src/app/services/driverServices/ticket.service";

@Component({
  selector: "app-map",
  templateUrl: "./map.component.html",
  styleUrls: ["./map.component.css"],
})
export class MapComponent implements OnInit {
  // Define our base layers so we can reference them multiple times
  streetMaps = tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    detectRetina: true,
    maxNativeZoom: 18,
    maxZoom: 100,
  });
  wMaps = tileLayer("http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png", {
    detectRetina: true,
    maxNativeZoom: 18,
    maxZoom: 100,
  });

  // Layers control object with our two base layers and the three overlay layers
  layersControl = {
    baseLayers: {
      "Street Maps": this.streetMaps,
      "Wikimedia Maps": this.wMaps,
    },
  };

  customZoom: number = 16;
  customCenter = latLng([43.14367132147207, 13.067582838096936]);

  // Set the initial set of displayed layers (we could also use the leafletLayers input binding for this)
  options = {
    layers: [this.streetMaps],
    zoom: this.customZoom,
    center: this.customCenter,
  };

  streetsLayers = [];

  automatic: boolean = false;

  subscription: boolean = false;

  constructor(
    private ticketService: TicketService,
    private zone: NgZone,
    private modalService: NgbModal,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.zone.run(() => {
      this.customZoom = 16;
      this.customCenter = latLng([43.14367132147207, 13.067582838096936]);
    });

    this.titleService.setTitle("ParkMe | Map");
    if (history.state.automatic != null) {
      this.automatic = history.state.automatic;
    }

    if (history.state.subscription != null) {
      this.subscription = history.state.subscription;
    }

    this.streetsLayers = [];
    this.ticketService.driverGetStreetList(this.subscription).subscribe(
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
                  iconUrl: "leaflet/marker-icon.png",
                  iconRetinaUrl: "leaflet/marker-icon-2x.png",
                  shadowUrl: "leaflet/marker-shadow.png",
                }),
              }
            )
              .bindTooltip(item.street.toString())
              .on("click", () =>
                this.automatic
                  ? this.automaticSearch(
                      item.coordinates.latitude,
                      item.coordinates.longitude
                    )
                  : this.getParks(item)
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

  backButton() {
    this.ngOnInit();
  }

  automaticSearchFromClick(e) {
    if (this.automatic) {
      this.automaticSearch(e.latlng.lat, e.latlng.lng);
    } else return null;
  }

  automaticSearch(latitude: number, longitude: number) {
    this.zone.run(() => this.openModalCoordinates(latitude, longitude));
  }

  getParks(streetInfo: { coordinates: any; street: any }) {
    this.zone.run(() => {
      this.customZoom = 18.5;
      this.customCenter = latLng([
        streetInfo.coordinates.latitude,
        streetInfo.coordinates.longitude,
      ]);
    });

    if (this.subscription) {
      this.ticketService
        .driverGetPersonalParkingLots(streetInfo.street)
        .subscribe(
          (success) => {
            console.log(success);
            let markers = [];
            success.forEach((item) => {
              if (item.personalParkingLotStatus !== "DISABLED") {
                let markerItem = this.createPersonalMarker(item).on(
                  "click",
                  () => this.zone.run(() => this.openModalBookParking(item))
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
    } else {
      this.ticketService.driverGetParkingLots(streetInfo.street).subscribe(
        (success) => {
          console.log(success);
          let markers = [];
          success.forEach((item) => {
            if (item.status !== "DISABLED") {
              let markerItem = this.createMarker(item).on("click", () =>
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
  }

  createPersonalMarker(item) {
    let html = "";
    if (!item.isHandicapParkingLot) {
      html =
        "<div class='marker-pin-free-personal'></div><i class='material-icons'>stop_circle</i>";
    } else {
      html =
        "<div class='marker-pin-free-personal'></div><i class='material-icons'>accessible_forward</i>";
    }

    return marker([item.coordinates.latitude, item.coordinates.longitude], {
      icon: divIcon({
        className: "custom-div-icon",
        html: html,
        iconSize: [30, 42],
        iconAnchor: [15, 42],
      }),
    }).bindTooltip(item.numberOfParkingLot.toString());
  }

  createMarker(item) {
    let html = "";
    if (
      !item.isHandicapParkingLot &&
      item.status == "FREE" &&
      item.sensorState == "OFF" &&
      item.pricePerHour == 0
    ) {
      html =
        "<div class='marker-pin-free'></div><i class='material-icons'>stop_circle</i>";
    } else if (
      !item.isHandicapParkingLot &&
      item.status != "FREE" &&
      item.pricePerHour == 0
    ) {
      html =
        "<div class='marker-pin-occupied'></div><i class='material-icons'>stop_circle</i>";
    } else if (
      !item.isHandicapParkingLot &&
      item.status == "FREE" &&
      item.sensorState == "OFF" &&
      item.pricePerHour > 0
    ) {
      html =
        "<div class='marker-pin-free'></div><i class='material-icons'>monetization_on</i>";
    } else if (
      !item.isHandicapParkingLot &&
      item.status != "FREE" &&
      item.pricePerHour > 0
    ) {
      html =
        "<div class='marker-pin-occupied'></div><i class='material-icons'>monetization_on</i>";
    } else if (
      item.isHandicapParkingLot &&
      item.status == "FREE" &&
      item.sensorState == "OFF"
    ) {
      html =
        "<div class='marker-pin-free'></div><i class='material-icons'>accessible_forward</i>";
    } else if (item.isHandicapParkingLot && item.status == "BOOKED") {
      html =
        "<div class='marker-pin-occupied'></div><i class='material-icons'>accessible_forward</i>";
    } else {
      console.table(item);
    }

    return marker([item.coordinates.latitude, item.coordinates.longitude], {
      icon: divIcon({
        className: "custom-div-icon",
        html: html,
        iconSize: [30, 42],
        iconAnchor: [15, 42],
      }),
    }).bindTooltip(item.numberOfParkingLot.toString());
  }

  openModalBookParking(street: any) {
    let modalRef = this.modalService.open(ConfirmParkingLotBookingComponent);
    modalRef.componentInstance.PARKINGLOT = street;
    modalRef.result.then(
      () => {
        console.log("Modal Confirm Parking Lot Closed");
        this.ngOnInit();
      },
      () => {
        console.log("Modal Confirm Parking Lot Dismissed");
      }
    );
  }

  openModalCoordinates(lat: number, lng: number) {
    let modalRef = this.modalService.open(CoordinatesModalComponent);
    modalRef.componentInstance.LAT = lat;
    modalRef.componentInstance.LNG = lng;
    modalRef.result.then(
      () => {
        console.log("Modal Closed");
      },
      () => {
        console.log("Modal Dismissed");
      }
    );
  }
}
