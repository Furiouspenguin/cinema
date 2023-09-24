package pm.practice.cinema.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pm.practice.cinema.domain.Reservation;
import pm.practice.cinema.dto.ReservationCreateCommandDto;
import pm.practice.cinema.dto.ReservationListItemDto;
import pm.practice.cinema.repository.ReservationRepository;
import pm.practice.cinema.repository.ScreeningRepository;

import java.util.List;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ScreeningRepository screeningRepository) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
    }

    public void saveReservation(ReservationCreateCommandDto command) {
        reservationRepository.save(mapRservationCreateCommandToReservation(command));
    }

    private Reservation mapRservationCreateCommandToReservation(ReservationCreateCommandDto command) {
        Reservation reservation = new Reservation();
        reservation.setName(command.getName());
        reservation.setScreening(screeningRepository.findById(command.getScreeningId())
                .orElseThrow(EntityNotFoundException::new));
        reservation.setSeatCount(command.getSeatCount());
        return reservation;
    }

    public List<ReservationListItemDto> getAllReservations() {
        return reservationRepository.findAll().stream().map(this::mapReservationToListItem).toList();
    }

    private ReservationListItemDto mapReservationToListItem(Reservation reservation) {
        ReservationListItemDto reservationListItemDto = new ReservationListItemDto();
        reservationListItemDto.setId(reservation.getId());
        reservationListItemDto.setName(reservation.getName());
        reservationListItemDto.setTitle(reservation.getScreening().getTitle());
        reservationListItemDto.setSeatCount(reservation.getSeatCount());
        reservationListItemDto.setScreeningDateTime(reservation.getScreening().getScreeningDateTime());
        return reservationListItemDto;
    }
}
