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
        validateStringNotNullOrEmpty(cityDto.getPostCode());
        checkArgumentNotNull(cityDto.getCityName());
        validateStringNotNullOrEmpty(cityDto.getCityName());
        checkArgumentNotNull(cityDto.getLatitude());
        checkArgumentNotNull(cityDto.getLongitude());
        if (cityRepository.findCityByPostCode(cityDto.getPostCode()).isPresent()) {
            throw new CityAlreadyExistsException("City with this post code already exists");
        }
    }
}
