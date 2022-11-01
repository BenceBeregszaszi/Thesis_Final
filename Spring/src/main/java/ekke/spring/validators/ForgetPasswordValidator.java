package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dto.ForgetPasswordDto;
import org.springframework.stereotype.Component;

@Component
public class ForgetPasswordValidator extends BaseValidator {

    public void validate(final ForgetPasswordDto forgetPasswordDto) {
        validateObject(forgetPasswordDto);
        validateStringNotNullOrEmpty(forgetPasswordDto.getReminder());
        validateStringNotNullOrEmpty(forgetPasswordDto.getNewPassword());
        validateStringNotNullOrEmpty(forgetPasswordDto.getEmail());
    }
}
