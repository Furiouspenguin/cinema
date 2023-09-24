package pm.practice.cinema.dto;

import lombok.Data;

@Data
public class ReservationCreateCommandDto {
    private String name;
    private Integer seatCount;
    private Long screeningId;
}
