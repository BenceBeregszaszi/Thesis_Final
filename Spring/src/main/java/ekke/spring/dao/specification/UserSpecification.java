package ekke.spring.dao.specification;

import ekke.spring.dao.entity.User;
import ekke.spring.dao.entity.User_;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSpecification implements Specification<User> {

    private String username;
    private String password;
    private String email;
    private String reminder;

    private Boolean isDisabled;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditions = new ArrayList<>();
        if(Objects.nonNull(username)) {
            conditions.add(searchWithUsername(criteriaBuilder, root));
        }
        if(Objects.nonNull(password)) {
            conditions.add(searchWithPassword(criteriaBuilder, root));
        }
        if(Objects.nonNull(email)) {
            conditions.add(searchWithEmail(criteriaBuilder, root));
        }
        if(Objects.nonNull(reminder)) {
            conditions.add(searchWithReminder(criteriaBuilder, root));
        }
        if(Objects.nonNull(isDisabled)) {
            conditions.add(searchWithIsDisabled(criteriaBuilder, root));
        }
        if (!conditions.isEmpty()) {
            return criteriaBuilder.and(conditions.toArray(new Predicate[conditions.size()]));
        }
        return null;
    }

    private Predicate searchWithUsername(CriteriaBuilder cb, Root<User> user) {
        return cb.equal(user.get(User_.USERNAME), username);
    }

    private Predicate searchWithPassword(CriteriaBuilder cb, Root<User> user) {
        return cb.equal(user.get(User_.PASSWORD), password);
    }

    private Predicate searchWithEmail(CriteriaBuilder cb, Root<User> user) {
        return cb.equal(user.get(User_.EMAIL), email);
    }

    private Predicate searchWithReminder(CriteriaBuilder cb, Root<User> user) {
        return cb.equal(user.get(User_.REMINDER), reminder);
    }

    private Predicate searchWithIsDisabled(CriteriaBuilder cb, Root<User> user) {
        return cb.equal(user.get(User_.IS_DISABLED), isDisabled);
    }
}
