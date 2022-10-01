package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.common.IdValidator;
import ekke.spring.common.exception.ValidationException;
import ekke.spring.conversion.UserConversionService;
import ekke.spring.dao.entity.User;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dto.UserDto;
import ekke.spring.service.exception.AuthenticationException;
import ekke.spring.service.exception.UserNotFoundException;
import ekke.spring.validators.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements CrudServices<UserDto> {

    @Autowired
    private UserConversionService userConversionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdValidator idValidator;

    @Autowired
    private UserDtoValidator userDtoValidator;

    @Override
    public UserDto add(final UserDto dto) {
        userDtoValidator.validate(dto);
        User savedUser = userRepository.save(userConversionService.UserDto2UserEntity(dto));
        return userConversionService.UserEntity2UserDto(savedUser);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(user -> userConversionService.UserEntity2UserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(final Long id) {
        idValidator.validateId(id);
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
        return userConversionService.UserEntity2UserDto(user);
    }

    @Override
    public UserDto update(final Long id, final UserDto dto) {
        idValidator.validateId(id);
        userDtoValidator.validate(dto);
        userDtoValidator.validateForUpdate(dto.getUsername(), dto.getPassword());
        User oldUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
        User newUser = setUserForUpdate(oldUser, userConversionService.UserDto2UserEntity(dto));
        userRepository.save(newUser);
        return userConversionService.UserEntity2UserDto(newUser);
    }

    @Override
    public void delete(final Long id) {
        idValidator.validateId(id);
        userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException(String.format("User with id %d not found", id)));
        userRepository.deleteById(id);
    }

    public UserDto getForAuthentication(final String username, final String password) {
        if (Objects.isNull(username) || Objects.isNull(password)){
            throw new ValidationException("User name or password is null");
        }
        User user = userRepository.findByUsernameAndPassword(username, password).
                orElseThrow(() -> new AuthenticationException("Authentication failed :Wrong Username or password"));
        return userConversionService.UserEntity2UserDto(user);
    }

    private User setUserForUpdate(final User oldUser, final User newUser){
        oldUser.setPassword(newUser.getPassword());
        return oldUser;
    }
}
