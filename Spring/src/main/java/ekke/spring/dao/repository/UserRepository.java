package ekke.spring.dao.repository;

import ekke.spring.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(final String username);

    Optional<User> findByIdAndIsDisabledFalse(final Long id);

    Optional<User> findByEmailAndReminder(final String email, final String reminder);
}
