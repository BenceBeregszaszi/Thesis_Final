package ekke.spring.dao.repository;

import ekke.spring.dao.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query
    Optional<Restaurant> findByNameAAndMaxSeatsNumber(final String name, final int maxSeatsNumber);
}
