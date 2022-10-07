package ekke.spring.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ekke.spring.conversion.SetDeserializer;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private Long id;

    private Date version;

    private String name;

    private Integer maxSeatsNumber;

    @JsonDeserialize(using = SetDeserializer.class)
    private Set<Long> cities;
}
