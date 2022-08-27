package ekke.spring.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private Long id;

    private Date version;

    private Long userId;

    private Integer seatNumber;

    private Long cityId;

    private Long restaurantId;

    private Date time;
}
