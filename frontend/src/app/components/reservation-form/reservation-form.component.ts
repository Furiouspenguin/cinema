import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import {ScreeningService} from "../../services/screening.service";
import {ScreeningSelectListItemModel} from "../../models/screening-select-list-item.model";
import {validationHandler} from "../../utils/validation-handler";

@Component({
  selector: 'app-reservation-form',
  templateUrl: './reservation-form.component.html',
  styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit{

  reservationForm!: FormGroup
  screenings!: ScreeningSelectListItemModel[]
  constructor(private formBuilder: FormBuilder,
              private reservationService: ReservationService,
              private screeningService: ScreeningService,
              private router: Router) {
    this.reservationForm = this.formBuilder.group({
      name: ['', Validators.required],
      seatCount: [1, [Validators.required, Validators.min(1)]],
      screeningId: [null, Validators.required]
    })
  }

  ngOnInit(): void {
    this.screeningService.fetchAllScreeningSelectItems().subscribe({
      next: data => {
        console.log(data);
        this.screenings = data
      },
      error: err => {
        console.error(err)
      }
    })
  }

  saveReservation() {
    console.log(this.reservationForm.value)

    this.reservationService.saveReservation(this.reservationForm.value).subscribe({
      error: err => {
        console.error(err)
        validationHandler(err, this.reservationForm)
      },
      complete: () => {
        this.reservationForm.reset()
        this.router.navigate(['reservation-list'])
      }
    })
  }
}
