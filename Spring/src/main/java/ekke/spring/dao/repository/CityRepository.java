package ekke.spring.dao.repository;

import ekke.spring.dao.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query
    Optional<City> findCityByPostCode(final String postCode);
}
