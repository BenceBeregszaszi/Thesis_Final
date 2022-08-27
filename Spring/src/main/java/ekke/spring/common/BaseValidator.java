package ekke.spring.common;

import ekke.spring.common.exception.ValidationException;

import java.util.Objects;

public class BaseValidator {
    protected void validateObject(Object object){
        if (Objects.isNull(object))
            throw new ValidationException("Given dto is null");
    }

    protected void checkArgumentNotNull(Object object){
        if (Objects.isNull(object))
            throw new ValidationException("Given argument is null");
    }
}
