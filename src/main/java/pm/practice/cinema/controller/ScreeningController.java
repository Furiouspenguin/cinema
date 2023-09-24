package pm.practice.cinema.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pm.practice.cinema.dto.ScreeningCreateCommandDto;
import pm.practice.cinema.dto.ScreeningListItemDto;
import pm.practice.cinema.dto.ScreeningSelectListItemDto;
import pm.practice.cinema.dto.SummaryDto;
import pm.practice.cinema.service.ScreeningService;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
@Slf4j
public class ScreeningController {
    private final ScreeningService screeningService;

    @Autowired
    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @PostMapping
    public ResponseEntity<Void> saveScreening(@RequestBody @Valid ScreeningCreateCommandDto command) {
        screeningService.saveScreening(command);
        log.info("http request, post, /api/screenings body: " + command.toString());
        log.info("New screening added");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScreeningListItemDto> getAllScreenings() {
        List<ScreeningListItemDto> screenings = screeningService.getAllScreenings();
        log.info("Screenings page requested");
        return screenings;
    }

    @GetMapping("/selectItems")
    public List<ScreeningSelectListItemDto> getAllScreeningSelectListItems() {
        List<ScreeningSelectListItemDto> screenings = screeningService.getAllScreeningSelectListItems();
        log.info("Reservation page requested");
        return screenings;
    }

    @GetMapping("/summaryList")
    public List<SummaryDto> getAllSummaryListItems() {
        List<SummaryDto> summaryList = screeningService.getAllSummaryListItems();
        log.info("Movie summary page requested");
        return summaryList;
    }


}
