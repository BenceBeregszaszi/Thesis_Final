package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.UserDto;

public class UserDtoValidator extends BaseValidator {

    public void validate(UserDto userDto) {
        validateObject(userDto);
        checkArgumentNotNull(userDto.getEmail());
        checkArgumentNotNull(userDto.getUsername());
        checkArgumentNotNull(userDto.getPassword());
    }
}
