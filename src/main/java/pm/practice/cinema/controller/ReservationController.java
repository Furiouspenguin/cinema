package pm.practice.cinema.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pm.practice.cinema.dto.ReservationCreateCommandDto;
import pm.practice.cinema.dto.ReservationListItemDto;
import pm.practice.cinema.service.ReservationService;
import pm.practice.cinema.validators.ReservationCreateCommandValidator;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationCreateCommandValidator reservationCreateCommandValidator;

    @Autowired
    public ReservationController(ReservationService reservationService,
                                 ReservationCreateCommandValidator reservationCreateCommandValidator) {
        this.reservationService = reservationService;
        this.reservationCreateCommandValidator = reservationCreateCommandValidator;
    }

    @InitBinder("reservationCreateCommandDto")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(reservationCreateCommandValidator);
    }

    @PostMapping
    public ResponseEntity<Void> saveReservation(@RequestBody @Valid ReservationCreateCommandDto command) {
        reservationService.saveReservation(command);
        log.info("Ticket(s) reserved");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<ReservationListItemDto> getAllReservations() {
        List<ReservationListItemDto> allReservations = reservationService.getAllReservations();
        log.info("Reservations page requested");
        return allReservations;
    }
}
