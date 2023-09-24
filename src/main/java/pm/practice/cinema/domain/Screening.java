package pm.practice.cinema.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @OneToMany(mappedBy = "screening")
    private Set<Reservation> reservations = new HashSet<>();
}
