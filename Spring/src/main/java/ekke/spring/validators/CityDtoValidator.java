package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dao.repository.CityRepository;
import ekke.spring.dto.CityDto;
import ekke.spring.service.exception.CityAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityDtoValidator extends BaseValidator {

    @Autowired
    CityRepository cityRepository;

    public void validate(final CityDto cityDto) {
        validateObject(cityDto);
        checkArgumentNotNull(cityDto.getPostCode());
    }

    public void validateForUpdate(final String postCode){
        if (cityRepository.findCityByPostCode(postCode).isPresent());
            throw new CityAlreadyExistsException("City with this post code already exists");
    }
}
