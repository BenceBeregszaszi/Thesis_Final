package ekke.spring.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ekke.spring.conversion.SetDeserializer;
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

    @JsonDeserialize(using = SetDeserializer.class)
    private Set<Long> restaurants;
}
