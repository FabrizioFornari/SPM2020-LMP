import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotificationService } from 'src/app/services/commonServices/notification.service';
import { ConfirmPresenceComponent } from '../../driverModal/confirm-presence/confirm-presence.component';
import { RefundComponentComponent } from '../../driverModal/refund-component/refund-component.component';
import { CheckParkComponent } from '../check-park/check-park.component';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css'],
})
export class NotificationComponent implements OnInit {
  @Input() NOTIFICATIONS: any[];

  constructor(
    public activeModal: NgbActiveModal,
    private notificationService: NotificationService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
  }

  showNotification(notification) {
    console.table(notification);
    this.markRead(notification.id);
    if (notification.categoryNotification == 'DRIVER_ABUSIVE_PARKING') {
      this.openModalConfirmPresence(notification);
    } else if (
      notification.categoryNotification == 'VIGILANT_ABUSIVE_PARKING' ||
      notification.categoryNotification == 'DRIVER_EXPIRING_TICKET' ||
      notification.categoryNotification == 'VIGILANT_EXPIRING_TICKET'
    ) {
      this.openModalCheckPark(notification);
    } else if (notification.categoryNotification == 'DRIVER_REFUNDED_TICKET') {
      this.openModalRefund(notification);
    }
  }

  openModalConfirmPresence(not) {
    const modalRef = this.modalService.open(ConfirmPresenceComponent);
    modalRef.componentInstance.notification = not;
    modalRef.result.then(
      () => {
        console.log('Modal ConfirmPresence Closed');
        this.activeModal.dismiss();
      },
      () => {
        console.log('Modal ConfirmPresence Dismissed');
        this.activeModal.dismiss();
      }
    );
  }

  openModalCheckPark(not) {
    const modalRef = this.modalService.open(CheckParkComponent);
    modalRef.componentInstance.notification = not;
    modalRef.result.then(
      () => {
        console.log('Modal Check Closed');
        this.activeModal.dismiss();
      },
      () => {
        console.log('Modal Check Dismissed');
        this.activeModal.dismiss();
      }
    );
  }

  openModalRefund(not) {
    this.activeModal.dismiss();
    const modalRef = this.modalService.open(RefundComponentComponent);
    modalRef.componentInstance.notification = not;
    modalRef.result.then(
      () => {
        console.log('Modal ConfirmPresence Closed');
        this.activeModal.dismiss();
      },
      () => {
        console.log('Modal ConfirmPresence Dismissed');
        this.activeModal.dismiss();
      }
    );
  }

  markRead(id) {
    this.notificationService.markNotificationRead(id).subscribe(
      (success) => {
        console.log(success);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
