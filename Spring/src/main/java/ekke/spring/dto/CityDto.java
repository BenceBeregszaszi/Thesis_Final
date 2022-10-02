package ekke.spring.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private Long id;

    private Date version;

    private String postCode;

    private String cityName;

    private Double longitude;

    private Double latitude;

    private Set<Long> restaurants;
}
