package pm.practice.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pm.practice.cinema.domain.Screening;
import pm.practice.cinema.dto.SummaryDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {

    List<Screening> findAllByOrderByScreeningDateTime();

    @Query("select sum(r.seatCount) from Screening s join s.reservations r where s.id = :id")
    Optional<Integer> getUsedSeatCount(Long id);

    @Query("select new pm.practice.cinema.dto.SummaryDto(" +
            "s.title, count(distinct s.screeningDateTime), sum(r.seatCount)) " +
            "from Screening s left join s.reservations r group by s.title")
    List<SummaryDto> fetchSummaryList();
}
