package pm.practice.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pm.practice.cinema.domain.Screening;
import pm.practice.cinema.dto.ScreeningCreateCommandDTo;
import pm.practice.cinema.repository.ScreeningRepository;

@Service
@Transactional
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public void saveScreening(ScreeningCreateCommandDTo command) {
        screeningRepository.save(mapScreeningCreateCommandToScreening(command));
    }

    private Screening mapScreeningCreateCommandToScreening(ScreeningCreateCommandDTo command) {
        Screening screening = new Screening();
        screening.setTitle(command.getTitle());
        screening.setScreeningDateTime(command.getScreeningDateTime());
        screening.setMaxSeatCount(command.getMaxSeatCount());
        screening.setPictureUrl(command.getPictureUrl());
        return screening;
    }
}
