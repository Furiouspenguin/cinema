package pm.practice.cinema.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "screening")
@Data
@NoArgsConstructor
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "screening_date_time")
    private LocalDateTime screeningDateTime;

    @Column(name = "max_seat_count")
    private Integer maxSeatCount;

    @Column(name = "picture_url", columnDefinition = "TEXT")
    private String pictureUrl;

}
