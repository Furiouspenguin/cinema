import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ReservationCreateCommandModel} from "../models/reservation-create-command.model";
import {Observable} from "rxjs";
import {ReservationListItemModel} from "../models/reservation-list-item.model";

const BASE_URL = 'http://localhost:8080/api/reservations';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  saveReservation(data: ReservationCreateCommandModel) {
    return this.http.post(BASE_URL, data)
  }

  fetchAllReservations(): Observable<ReservationListItemModel[]> {
    return this.http.get<ReservationListItemModel[]>(BASE_URL)
  }
}
