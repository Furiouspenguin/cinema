package pm.practice.cinema.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreeningListItemDto {
    private Long id;
    private String pictureUrl;
    private String title;
    private LocalDateTime screeningDateTime;
    private Integer maxSeatCount;
    private Integer freeSeatCount;
}
