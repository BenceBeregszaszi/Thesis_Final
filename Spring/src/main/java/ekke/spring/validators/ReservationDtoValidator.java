package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.ReservationDto;
import org.springframework.stereotype.Component;

@Component
public class ReservationDtoValidator extends BaseValidator {

    public void validate(final ReservationDto reservationDto) {
        validateObject(reservationDto);
        checkArgumentNotNull(reservationDto.getTime());
        checkArgumentNotNull(reservationDto.getCityId());
        checkArgumentNotNull(reservationDto.getUserId());
        checkArgumentNotNull(reservationDto.getRestaurantId());
        checkArgumentNotNull(reservationDto.getSeatNumber());
    }
}
