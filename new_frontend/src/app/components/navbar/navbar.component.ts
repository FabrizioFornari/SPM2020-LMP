import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotificationComponent } from 'src/app/modals/commonModal/notification/notification.component';
import { IsAdminService } from 'src/app/services/authGuard/is-admin.service';
import { IsDriverService } from 'src/app/services/authGuard/is-driver.service';
import { IsLoggedInService } from 'src/app/services/authGuard/is-logged-in.service';
import { IsManagerService } from 'src/app/services/authGuard/is-manager.service';
import { IsVigilantService } from 'src/app/services/authGuard/is-vigilant.service';
import { NotificationService } from 'src/app/services/commonServices/notification.service';
import { JwtHelperService } from '@auth0/angular-jwt';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  isLogged: boolean = false;
  isManager: boolean = false;
  isAdmin: boolean = false;
  isDriver: boolean = false;
  isVigilant: boolean = false;

  notificationsList = [];

  newNotifications: boolean = false;

  constructor(
    private modalService: NgbModal,
    private router: Router,
    private IsLoggedInService: IsLoggedInService,
    private IsAdminService: IsAdminService,
    private IsManagerService: IsManagerService,
    private IsDriverService: IsDriverService,
    private isVigilantService: IsVigilantService,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    $(function () {
      $(document).on('click', function (event) {
        var target = $(event.target);
        var _mobileMenuOpen = $('.navbar-collapse').hasClass('show');
        if (_mobileMenuOpen === true && !target.hasClass('navbar-toggler')) {
          $('div.navbar-toggler').trigger('click');
        }
      });
    });

    this.isLogged = this.IsLoggedInService.notLoggedIn();
    this.isAdmin = this.IsAdminService.isAdmin();
    this.isManager = this.IsManagerService.isManager();
    this.isDriver = this.IsDriverService.isDriver();
    this.isVigilant = this.isVigilantService.isVigilant();

    if (this.loggedIn()) {
      this.notification();
    } else {
      return null;
    }

    this.notificationService.notification$.subscribe(() => {
      this.notification();
    });
  }

  loggedIn() {
    const token = localStorage.getItem('token');
    const jwtHelper: JwtHelperService = new JwtHelperService();
    return !jwtHelper.isTokenExpired(token);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['login']).then(() => window.location.reload());
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
          if (this.notificationsList[index].statusNotification == 'NEW') {
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

  showNotification() {
    this.notification();
    const modalRef = this.modalService.open(NotificationComponent);
    modalRef.componentInstance.NOTIFICATIONS = this.notificationsList;
    modalRef.result.then(
      () => {
        console.log('Modal Notification Closed');
        this.notification();
      },
      () => {
        console.log('Modal Notification Dismissed');
        this.notification();
      }
    );
  }
}
