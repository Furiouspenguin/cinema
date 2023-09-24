import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ScreeningService} from "../../services/screening.service";
import {validationHandler} from "../../utils/validation-handler";
import {Router} from "@angular/router";

@Component({
  selector: 'app-screening-form',
  templateUrl: './screening-form.component.html',
  styleUrls: ['./screening-form.component.css']
})
export class ScreeningFormComponent {

  screeningForm!: FormGroup

  constructor(private formBuilder: FormBuilder,
              private screeningService: ScreeningService,
              private router: Router) {
    this.screeningForm = this.formBuilder.group({
      title: ['', Validators.required],
      screeningDateTime: [null, Validators.required],
      maxSeatCount: [0, [Validators.required, Validators.max(200)]],
      pictureUrl: ['']
    })
  }

  saveScreening() {
    console.log('Current value:', this.screeningForm.value)
    this.screeningService.saveScreening(this.screeningForm.value).subscribe({
      error: err => {
        console.error(err);
        validationHandler(err, this.screeningForm);
      },
      complete: () => {
        this.screeningForm.reset()
        this.router.navigate(['screening-list'])
      }
    })
  }
}
