package ekke.spring.dao.repository;

import ekke.spring.dao.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("SELECT SUM(r.seatNumber) FROM reservation r WHERE r.restaurant.id = :restaurantId AND r.id <> :id")
    Optional<Integer> findAllSeatNumberByRestaurantId(final long restaurantId, final long id);


    @Query("SELECT r FROM reservation r WHERE r.person.id = :userId")
    List<Reservation> findByUser(final long userId);

    @Query("SELECT r FROM  reservation r WHERE r.city.id = :cityId")
    List<Reservation> findByCityId(final Long cityId);
}
