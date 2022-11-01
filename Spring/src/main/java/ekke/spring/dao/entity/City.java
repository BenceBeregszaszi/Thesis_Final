package ekke.spring.dao.entity;

import ekke.spring.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "city")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity {

    @Column(name = "postCode", nullable = false, length = 4)
    private String postCode;

    @Column(name = "cityName", nullable = false, length = 200)
    private String cityName;

    @ManyToMany(mappedBy = "cities", cascade = CascadeType.REMOVE)
    private Set<Restaurant> restaurants;
}
