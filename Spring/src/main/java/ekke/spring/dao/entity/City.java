package ekke.spring.dao.entity;

import ekke.spring.common.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity {

    @Column(name = "postCode", nullable = false, length = 4)
    private String postCode;

    @ManyToMany(mappedBy = "cities")
    private Set<Restaurant> restaurants;
}
