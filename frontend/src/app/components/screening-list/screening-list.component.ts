import {Component, OnInit} from '@angular/core';
import {ScreeningListItemModel} from "../../models/screening-list-item.model";
import {ScreeningService} from "../../services/screening.service";
import {isValidUrl} from "../../utils/url-validator";
import {dateTimeFormatter} from "../../utils/date-time-formatter";

@Component({
  selector: 'app-screening-list',
  templateUrl: './screening-list.component.html',
  styleUrls: ['./screening-list.component.css']
})
export class ScreeningListComponent implements OnInit{
  screenings!: ScreeningListItemModel[]

  constructor(private screeningService: ScreeningService) {
  }

  ngOnInit(): void {
    this.screeningService.fetchAllScreenings().subscribe({
      next: data => {
        console.log('Incoming data', data)
        this.screenings = data;
      },
      error: err => {
        console.error(err);
      },
      complete: () => {
        console.log('Received data', this.screenings);
      }
    })
  }


  protected readonly isValidUrl = isValidUrl;
  protected readonly dateTimeFormatter = dateTimeFormatter;
}
