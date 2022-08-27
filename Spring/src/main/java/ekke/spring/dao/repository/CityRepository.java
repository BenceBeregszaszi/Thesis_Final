package ekke.spring.dao.repository;

import ekke.spring.dao.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
