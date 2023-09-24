package pm.practice.cinema.validators;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pm.practice.cinema.domain.Screening;
import pm.practice.cinema.dto.ReservationCreateCommandDto;
import pm.practice.cinema.repository.ScreeningRepository;

@Component
public class ReservationCreateCommandValidator implements Validator {

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ReservationCreateCommandValidator(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationCreateCommandDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservationCreateCommandDto command = (ReservationCreateCommandDto) target;

        Screening screening= screeningRepository.findById(command.getScreeningId())
                .orElseThrow(EntityNotFoundException::new);

        int leftoverSeats = screening.getMaxSeatCount() - screeningRepository.getUsedSeatCount(screening.getId())
                .orElse(0);
        if (leftoverSeats < command.getSeatCount()) {
            errors.rejectValue("seatCount", "seatCount.over.max");
        }
    }
}
