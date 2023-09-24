package pm.practice.cinema.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationListItemDto {
    private Long id;
    private String name;
    private String title;
    private Integer seatCount;
    private LocalDateTime screeningDateTime;
}
