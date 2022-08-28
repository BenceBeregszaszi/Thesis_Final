package ekke.spring.dao.repository;

import ekke.spring.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query
    Optional<User> findByUsernameAndPassword(final String username, final String password);
}
