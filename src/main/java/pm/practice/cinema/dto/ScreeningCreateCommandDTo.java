package pm.practice.cinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreeningCreateCommandDTo {
    private String title;
    private LocalDateTime screeningDateTime;
    private Integer maxSeatCount;
    private String pictureUrl;
}
