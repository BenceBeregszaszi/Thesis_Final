package ekke.spring.common;

import ekke.spring.common.exception.ValidationException;

import java.util.Objects;

public class BaseValidator {
    protected void validateObject(Object object) {
        if (Objects.isNull(object))
            throw new ValidationException("Given dto is null");
    }

    protected void checkArgumentNotNull(Object object) {
        if (Objects.isNull(object))
            throw new ValidationException("Given argument is null");
    }

    protected void validateStringNotNullOrEmpty(final String string){
        if (Objects.isNull(string) || string.isEmpty()){
            throw new ValidationException("Given string is null");
        }
    }
}
