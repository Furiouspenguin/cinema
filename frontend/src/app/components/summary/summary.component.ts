import {Component, OnInit} from '@angular/core';
import {SummaryModel} from "../../models/summary.model";
import {ScreeningService} from "../../services/screening.service";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit{
  summaryList!: SummaryModel[]
  constructor(private screeningService:ScreeningService) {
  }

  ngOnInit(): void {
    this.screeningService.fetchSummaryList().subscribe({
      next: data => {
        this.summaryList = data;
      },
      error: err => {
        console.error(err)
      },
      complete: () => console.log(this.summaryList)
    })
  }
}
