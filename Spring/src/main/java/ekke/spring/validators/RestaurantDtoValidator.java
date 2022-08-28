package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.RestaurantDto;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDtoValidator extends BaseValidator {

    public void validate(final RestaurantDto restaurantDto){
        validateObject(restaurantDto);
        checkArgumentNotNull(restaurantDto.getName());
        checkArgumentNotNull(restaurantDto.getMaxSeatsNumber());
    }
}
