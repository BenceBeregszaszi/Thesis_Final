package ekke.spring.dao.entity;

import ekke.spring.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User person;

    @Column(nullable = false, name = "seatNumber")
    private Integer seatNumber;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;

    @OneToOne
    @JoinColumn(name = "restaurantId",referencedColumnName = "id")
    private Restaurant restaurant;

    @Column(nullable = false, name = "time")
    private Date time;
}
