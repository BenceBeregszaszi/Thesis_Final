package ekke.spring.dao;

import ekke.spring.common.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User person;

    @Column(name = "seatNumber", nullable = false)
    private Integer seatNumber;

    @OneToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id")
    private City city;

    @OneToOne
    @JoinColumn(name = "restaurantId",referencedColumnName = "id")
    private Restaurant restaurant;

    @Column(name = "time")
    private Date time;
}
