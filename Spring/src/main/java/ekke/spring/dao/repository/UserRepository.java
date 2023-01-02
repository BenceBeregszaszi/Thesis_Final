package ekke.spring.dao.repository;

import ekke.spring.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECt u FROM user_details u WHERE u.username = :username AND  u.password = :password AND u.isDisabled = FALSE")
    Optional<User> findByUsernameAndPassword(final String username, final String password);

    @Query("SELECT u FROM user_details u WHERE u.email = :email AND u.isDisabled = TRUE")
    Optional<User> findByEmail(final String email);

    Optional<User> findByUsername(final String username);

    Optional<User> findByIdAndIsDisabledFalse(final Long id);

    Optional<User> findByEmailAndReminder(final String email, final String reminder);
}
