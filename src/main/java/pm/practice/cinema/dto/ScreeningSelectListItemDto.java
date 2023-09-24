package pm.practice.cinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreeningSelectListItemDto {
    private Long id;
    private String title;
    private LocalDateTime screeningDateTime;
}
