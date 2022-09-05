package ekke.spring.dao.entity;

import ekke.spring.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity {

    @Column(name = "postCode", nullable = false, length = 4)
    private String postCode;

    @ManyToMany(mappedBy = "cities")
    private Set<Restaurant> restaurants;
}
