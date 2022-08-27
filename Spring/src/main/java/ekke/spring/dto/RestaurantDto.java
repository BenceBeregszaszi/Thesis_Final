package ekke.spring.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Long id;

    private Date version;

    private String name;

    private Integer maxSeatsNumber;

    private Set<Long> cities;
}
