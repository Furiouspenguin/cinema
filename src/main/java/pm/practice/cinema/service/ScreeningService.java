package pm.practice.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pm.practice.cinema.domain.Screening;
import pm.practice.cinema.dto.ScreeningCreateCommandDto;
import pm.practice.cinema.dto.ScreeningListItemDto;
import pm.practice.cinema.dto.ScreeningSelectListItemDto;
import pm.practice.cinema.dto.SummaryDto;
import pm.practice.cinema.repository.ScreeningRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public void saveScreening(ScreeningCreateCommandDto command) {
        screeningRepository.save(mapScreeningCreateCommandToScreening(command));
    }

    private Screening mapScreeningCreateCommandToScreening(ScreeningCreateCommandDto command) {
        Screening screening = new Screening();
        screening.setTitle(command.getTitle());
        screening.setScreeningDateTime(command.getScreeningDateTime());
        screening.setMaxSeatCount(command.getMaxSeatCount());
        screening.setPictureUrl(command.getPictureUrl());
        return screening;
    }

    public List<ScreeningListItemDto> getAllScreenings() {
        return screeningRepository.findAllByOrderByScreeningDateTime().stream()
                .map(this::mapScreeningToScreeningListItem).toList();
    }

    private ScreeningListItemDto mapScreeningToScreeningListItem(Screening screening) {
        ScreeningListItemDto screeningListItemDto = new ScreeningListItemDto();
        screeningListItemDto.setId(screening.getId());
        screeningListItemDto.setTitle(screening.getTitle());
        screeningListItemDto.setScreeningDateTime(screening.getScreeningDateTime());
        screeningListItemDto.setPictureUrl(screening.getPictureUrl());
        screeningListItemDto.setMaxSeatCount(screening.getMaxSeatCount());
        screeningListItemDto.setFreeSeatCount(
                screening.getMaxSeatCount() - screeningRepository.getUsedSeatCount(screening.getId()).orElse(0));

        return screeningListItemDto;
    }

    public List<ScreeningSelectListItemDto> getAllScreeningSelectListItems() {
        return screeningRepository.findAllByOrderByScreeningDateTime().stream()
                .map(this::mapScreeningToScreeningSelectListItem).toList();
    }

    private ScreeningSelectListItemDto mapScreeningToScreeningSelectListItem(Screening screening) {
        ScreeningSelectListItemDto screeningSelectListItemDto = new ScreeningSelectListItemDto();
        screeningSelectListItemDto.setId(screening.getId());
        screeningSelectListItemDto.setTitle(screening.getTitle());
        screeningSelectListItemDto.setScreeningDateTime(screening.getScreeningDateTime());
        return screeningSelectListItemDto;
    }

    public List<SummaryDto> getAllSummaryListItems() {
        return screeningRepository.fetchSummaryList();
    }
}
