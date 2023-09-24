import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ScreeningCreateCommandModel} from "../models/screening-create-command.model";
import {Observable} from "rxjs";
import {ScreeningListItemModel} from "../models/screening-list-item.model";
import {ScreeningSelectListItemModel} from "../models/screening-select-list-item.model";
import {SummaryModel} from "../models/summary.model";

const BASE_URL = 'http://localhost:8080/api/screenings';

@Injectable({
  providedIn: 'root'
})
export class ScreeningService {

  constructor(private http: HttpClient) { }
  saveScreening(data: ScreeningCreateCommandModel) {
    return this.http.post(BASE_URL, data)
  }

  fetchAllScreenings(): Observable<ScreeningListItemModel[]> {
    return this.http.get<ScreeningListItemModel[]>(BASE_URL)
  }

  fetchAllScreeningSelectItems(): Observable<ScreeningSelectListItemModel[]> {
    return this.http.get<ScreeningSelectListItemModel[]>(BASE_URL + '/selectItems')
  }

  fetchSummaryList(): Observable<SummaryModel[]> {
    return this.http.get<SummaryModel[]>(BASE_URL + '/summaryList')
  }
}
