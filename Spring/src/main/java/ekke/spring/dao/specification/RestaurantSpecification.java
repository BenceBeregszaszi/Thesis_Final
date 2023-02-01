package ekke.spring.dao.specification;

import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.entity.Restaurant_;
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
public class RestaurantSpecification implements Specification<Restaurant> {

    private String name;

    private Integer maxSeatNumber;
    @Override
    public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> conditions = new ArrayList<>();
        if (Objects.nonNull(name)) {
            conditions.add(searchWithName(criteriaBuilder, root));
        }
        if (Objects.nonNull(maxSeatNumber)) {
            conditions.add(searchWithMaxSeatNumber(criteriaBuilder, root));
        }
        if (!conditions.isEmpty()) {
            return criteriaBuilder.and(conditions.toArray(new Predicate[conditions.size()]));
        }
        return null;
    }

    private Predicate searchWithName(CriteriaBuilder cb, Root<Restaurant> restaurant) {
        return cb.equal(restaurant.get(Restaurant_.NAME), name);
    }

    private Predicate searchWithMaxSeatNumber(CriteriaBuilder cb, Root<Restaurant> restaurant) {
        return cb.equal(restaurant.get(Restaurant_.MAX_SEATS_NUMBER), maxSeatNumber);
    }
}
