package ekke.spring.dao.repository;

import ekke.spring.dao.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query
    Optional<Reservation> findByRestaurantAndTime(final long restaurantId, final Date time);
}
