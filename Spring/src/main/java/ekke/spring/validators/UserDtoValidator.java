package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dto.UserDto;
import ekke.spring.service.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoValidator extends BaseValidator {

    @Autowired
    private UserRepository userRepository;

    public void validate(final UserDto userDto) {
        validateObject(userDto);
        checkArgumentNotNull(userDto.getEmail());
        checkArgumentNotNull(userDto.getUsername());
        checkArgumentNotNull(userDto.getPassword());
    }

    public void validateForUpdate(final String username, final String password){
        if (userRepository.findByUsernameAndPassword(username, password).isPresent())
            throw new UserAlreadyExistsException(String.format("This user %s with password %s already occupied", username,password));
    }
}
