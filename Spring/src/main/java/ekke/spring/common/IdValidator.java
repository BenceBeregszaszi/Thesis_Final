package ekke.spring.common;

import ekke.spring.common.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IdValidator {

    public void validateId(final Long id){
        if (Objects.isNull(id))
            throw new ValidationException("Given id is null");
    }
}
