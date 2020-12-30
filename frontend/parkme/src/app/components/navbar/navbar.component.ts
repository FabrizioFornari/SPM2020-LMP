import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/services/notification.service';
import { UnifiedLoginService } from 'src/app/services/unified-login.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConfirmPresenceComponent } from 'src/app/modal/confirm-presence/confirm-presence.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  notificationsList = [];

  constructor(
    private unifiedlogin: UnifiedLoginService,
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

    this.unifiedlogin.loggedIn$.subscribe((value: boolean) => {
      if (value) {
        this.notification();
      } else {
        return null;
      }
    });
  }

  notification() {
    this.notificationsList = [];
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
      },
      (error) => {
        console.log(error);
      }
    );
  }

  notificationAction(not) {
    if (not.categoryNotification == 'PARKING') {
      this.openModalConfirmPresence(not)
    } else {
      console.log('APRI ALTRI MODAL');
    }
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
}
