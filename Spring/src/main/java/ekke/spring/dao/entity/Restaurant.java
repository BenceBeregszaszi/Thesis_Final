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
public class Restaurant extends BaseEntity {

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "maxSeatsNumber", nullable = false)
    private Integer maxSeatsNumber;

    @ManyToMany
    @JoinTable(
            name = "cityLink",
            joinColumns = @JoinColumn(name = "restaurantId"),
            inverseJoinColumns = @JoinColumn(name = "cityId")
    )
    private Set<City> cities;
}
