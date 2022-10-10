package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.repository.ReservationRepository;
import ekke.spring.dao.repository.RestaurantRepository;
import ekke.spring.dto.ReservationDto;
import ekke.spring.service.exception.NoMoreFreeSpaceException;
import ekke.spring.service.exception.ReservationAlreadyExists;
import ekke.spring.service.exception.RestaurantAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ReservationDtoValidator extends BaseValidator {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validate(final ReservationDto reservationDto) {
        validateObject(reservationDto);
        checkArgumentNotNull(reservationDto.getTime());
        checkArgumentNotNull(reservationDto.getCityId());
        checkArgumentNotNull(reservationDto.getUserId());
        checkArgumentNotNull(reservationDto.getRestaurantId());
        checkArgumentNotNull(reservationDto.getSeatNumber());
 }

    public void validateCreateReservation(final long id, final long restaurantId, final int seatNumber) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new RestaurantAlreadyExistsException(String.format("Restaurant with id %d is already exists", restaurantId)));
        int allReservedSeatNumber = seatNumber + reservationRepository.findAllSeatNumberByRestaurantId(restaurantId, id).orElse(0);
        if (allReservedSeatNumber > restaurant.getMaxSeatsNumber())
            throw new NoMoreFreeSpaceException("Not enough free space in the restaurant");
    }
}
