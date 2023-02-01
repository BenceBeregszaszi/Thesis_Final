package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dao.repository.RestaurantRepository;
import ekke.spring.dao.specification.RestaurantSpecification;
import ekke.spring.dto.RestaurantDto;
import ekke.spring.service.exception.ReservationAlreadyExists;
import ekke.spring.service.exception.RestaurantAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantDtoValidator extends BaseValidator {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validate(final RestaurantDto restaurantDto) {
        validateObject(restaurantDto);
        checkArgumentNotNull(restaurantDto.getName());
        validateStringNotNullOrEmpty(restaurantDto.getName());
        checkArgumentNotNull(restaurantDto.getMaxSeatsNumber());
        validateStringNotNullOrEmpty(restaurantDto.getAddress());
        RestaurantSpecification specification = new RestaurantSpecification(restaurantDto.getName(), restaurantDto.getMaxSeatsNumber());

        if (!restaurantRepository.findAll(specification).isEmpty()){
            throw new RestaurantAlreadyExistsException(String.format("Restaurant with name %s is already exists", restaurantDto.getName()));
        }
    }
}
