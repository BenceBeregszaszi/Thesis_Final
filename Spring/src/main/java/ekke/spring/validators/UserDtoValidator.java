package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dao.entity.User;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dao.specification.UserSpecification;
import ekke.spring.dto.UserDto;
import ekke.spring.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDtoValidator extends BaseValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(final UserDto userDto) {
        validateObject(userDto);
        checkArgumentNotNull(userDto.getEmail());
        validateStringNotNullOrEmpty(userDto.getEmail());
        checkArgumentNotNull(userDto.getUsername());
        validateStringNotNullOrEmpty(userDto.getUsername());
        checkArgumentNotNull(userDto.getPassword());
        validateStringNotNullOrEmpty(userDto.getPassword());
        checkArgumentNotNull(userDto.getAuthority());
        validateStringNotNullOrEmpty(userDto.getReminder());
        UserSpecification specification = new UserSpecification();
        specification.setUsername(userDto.getUsername());
        specification.setPassword(userDto.getPassword());
        specification.setIsDisabled(false);
        if (!userRepository.findAll(specification).isEmpty())
            throw new UserAlreadyExistsException(String.format("This user %s name already occupied",
                    userDto.getUsername()));
    }
}
