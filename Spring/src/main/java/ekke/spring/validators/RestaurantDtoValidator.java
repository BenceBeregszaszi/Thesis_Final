package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.RestaurantDto;

public class RestaurantDtoValidator extends BaseValidator {

    public void validate(RestaurantDto restaurantDto){
        validateObject(restaurantDto);
        checkArgumentNotNull(restaurantDto.getName());
        checkArgumentNotNull(restaurantDto.getMaxSeatsNumber());
    }
}
