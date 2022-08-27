package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.CityDto;
import org.springframework.stereotype.Component;

@Component
public class CityDtoValidator extends BaseValidator {
    public void validate(final CityDto cityDto) {
        validateObject(cityDto);
        checkArgumentNotNull(cityDto.getPostCode());
    }
}
