package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dao.repository.RestaurantRepository;
import ekke.spring.dto.RestaurantDto;
import ekke.spring.service.exception.ReservationAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDtoValidator extends BaseValidator {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validate(final RestaurantDto restaurantDto){
        validateObject(restaurantDto);
        checkArgumentNotNull(restaurantDto.getName());
        checkArgumentNotNull(restaurantDto.getMaxSeatsNumber());
    }

    public void validateForUpdate(final String name, final int maxSeatsNumber){
        if (restaurantRepository.findByNameAndMaxSeatsNumber(name, maxSeatsNumber).isPresent())
            throw new ReservationAlreadyExists(String.format("Restaurant with name %s is already exists", name));
    }
}
