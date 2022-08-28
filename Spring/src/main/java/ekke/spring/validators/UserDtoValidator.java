package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoValidator extends BaseValidator {

    public void validate(final UserDto userDto) {
        validateObject(userDto);
        checkArgumentNotNull(userDto.getEmail());
        checkArgumentNotNull(userDto.getUsername());
        checkArgumentNotNull(userDto.getPassword());
    }
}
