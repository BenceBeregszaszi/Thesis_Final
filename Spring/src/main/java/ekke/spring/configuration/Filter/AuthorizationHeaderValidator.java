package ekke.spring.configuration.Filter;

import ekke.spring.common.BaseValidator;
import ekke.spring.common.exception.ValidationException;
import org.springframework.stereotype.Component;


@Component
public class AuthorizationHeaderValidator extends BaseValidator {
    private final String prefix = "Bearer";
    private final String authorizationHeaderPattern = "^" + prefix + " .+$";

    public void validate(final String object) {
        validateStringNotNullOrEmpty(object);
        if (!object.matches(authorizationHeaderPattern)) {
            throw new ValidationException("Invalid authorization header: not matching expected header pattern");
        }
    }
}
