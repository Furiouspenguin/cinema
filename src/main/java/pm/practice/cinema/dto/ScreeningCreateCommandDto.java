package pm.practice.cinema.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreeningCreateCommandDto {
    private String title;
    private LocalDateTime screeningDateTime;
    @Min(3)
    private Integer maxSeatCount;
    private String pictureUrl;
}
