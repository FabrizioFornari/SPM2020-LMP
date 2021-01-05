import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmPresenceComponent } from 'src/app/modal/confirm-presence/confirm-presence.component';
import { JwtHelperService } from '@auth0/angular-jwt';
import { VigilantCheckParkComponent } from 'src/app/modal/vigilant-check-park/vigilant-check-park.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  notificationsList = [];

  newNotifications: boolean = false;

  constructor(
    private notificationService: NotificationService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    //per fare in modo che il menu si chiuda quando clicco all'esterno
    //ho installato anche @types/jquery
    //ho aggiunto "jquery" nei types di tsconfig.app.json

    $(document).ready(function () {
      $(document).click(function (event) {
        var target = $(event.target);
        var _mobileMenuOpen = $('.navbar-collapse').hasClass('show');
        if (_mobileMenuOpen === true && !target.hasClass('navbar-toggler')) {
          $('button.navbar-toggler').click();
        }
      });
    });

    if (this.loggedIn) {
      this.notification();
    } else {
      return null;
    }
  }

  loggedIn() {
    const token = localStorage.getItem('token');
    const jwtHelper: JwtHelperService = new JwtHelperService();
    return jwtHelper.isTokenExpired(token);
  }

  notification() {
    this.notificationsList = [];
    this.newNotifications = false;
    this.notificationService.getNotificationFromDB().subscribe(
      (data) => {
        console.log(data);
        data.forEach((element) => {
          let readable_date = `${new Date(element.timestamp).toLocaleDateString(
            'it-IT'
          )} (${new Date(element.timestamp).toLocaleTimeString('it-IT')})`;
          element.timestamp = readable_date;
          this.notificationsList.push(element);
        });
        this.notificationsList = this.notificationsList.reverse();
        for (let index = 0; index < this.notificationsList.length; index++) {
          if (this.notificationsList[index].statusNotification == "NEW") {
            this.newNotifications = true;
            break;
          }
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

  notificationAction(not) {
    this.markRead(not.id);
    if (not.categoryNotification == 'DRIVER_ABUSIVE_PARKING') {
      this.openModalConfirmPresence(not)
    } else if (not.categoryNotification == 'VIGILANT_ABUSIVE_PARKING') {
      this.openModalVigilantCheckPark(not)
    } else if(not.categoryNotification == 'DRIVER_EXPIRING_TICKET') {
      this.openModalVigilantCheckPark(not)
    } else if(not.categoryNotification == 'VIGILANT_EXPIRING_TICKET'){
      this.openModalVigilantCheckPark(not);
    }
     else {
      alert('ANOTHER MODAL');
    }
  }

  markRead(id){
    this.notificationService.markNotificationRead(id).subscribe(
      (success) => {
        console.log(success);
        this.notification();
      },
      (error) => {
        console.log(error);
      }
    )
  }

  openModalConfirmPresence(not) {
    const modalRef = this.modalService.open(ConfirmPresenceComponent);
    modalRef.componentInstance.notification = not;
    modalRef.result.then(
      () => {
        console.log('Modal ConfirmPresence Closed');
      },
      () => {
        console.log('Modal ConfirmPresence Dismissed');
      }
    );
  }

  openModalVigilantCheckPark(not) {
    const modalRef = this.modalService.open(VigilantCheckParkComponent);
    modalRef.componentInstance.notification = not;
    modalRef.result.then(
      () => {
        console.log('Modal VigilantCheck Closed');
      },
      () => {
        console.log('Modal VigilantCheck Dismissed');
      }
    );
  }

}
