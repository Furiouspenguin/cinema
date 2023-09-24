import {Component, OnInit} from '@angular/core';
import {ReservationListItemModel} from "../../models/reservation-list-item.model";
import {ReservationService} from "../../services/reservation.service";
import {dateTimeFormatter} from "../../utils/date-time-formatter";

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit{
  reservations!: ReservationListItemModel[]

  constructor(private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.reservationService.fetchAllReservations().subscribe({
      next: data => {
        this.reservations = data;
      },
      error: err => {
        console.error(err);
      },
      complete: () => {
        console.log(this.reservations);
      }
    })
  }

  protected readonly dateTimeFormatter = dateTimeFormatter;
}
