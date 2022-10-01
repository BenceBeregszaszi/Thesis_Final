package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.AuthenticationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationRequestValidator extends BaseValidator {

    public void validate(final AuthenticationRequestDto authenticationRequestDto) {
        validateObject(authenticationRequestDto);
        checkArgumentNotNull(authenticationRequestDto.getUsername());
        checkArgumentNotNull(authenticationRequestDto.getPassword());
    }
}
