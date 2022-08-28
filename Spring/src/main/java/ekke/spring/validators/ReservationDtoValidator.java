package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dao.repository.ReservationRepository;
import ekke.spring.dto.ReservationDto;
import ekke.spring.service.exception.ReservationAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReservationDtoValidator extends BaseValidator {

    @Autowired
    private ReservationRepository reservationRepository;

    public void validate(final ReservationDto reservationDto) {
        validateObject(reservationDto);
        checkArgumentNotNull(reservationDto.getTime());
        checkArgumentNotNull(reservationDto.getCityId());
        checkArgumentNotNull(reservationDto.getUserId());
        checkArgumentNotNull(reservationDto.getRestaurantId());
        checkArgumentNotNull(reservationDto.getSeatNumber());
    }

    public void validateForUpdate(final Date time, final long restaurantId) {
        if (reservationRepository.findByRestaurantAndTime(restaurantId, time).isPresent())
            throw new ReservationAlreadyExists(String.format("Reservation with this date %t with this restaurantId %d", time, restaurantId));
    }
}
