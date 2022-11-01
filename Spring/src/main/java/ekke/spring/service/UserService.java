package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.common.exception.ValidationException;
import ekke.spring.conversion.UserConversionService;
import ekke.spring.dao.entity.User;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dto.ForgetPasswordDto;
import ekke.spring.dto.UserDto;
import ekke.spring.service.exception.UserNotFoundException;
import ekke.spring.validators.ForgetPasswordValidator;
import ekke.spring.validators.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements CrudServices<UserDto> {

    @Autowired
    private UserConversionService userConversionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDtoValidator userDtoValidator;

    @Autowired
    private ForgetPasswordValidator forgetPasswordValidator;

    @Override
    public UserDto add(final UserDto dto) {
        userDtoValidator.validate(dto);
        dto.setIsDisabled(false);
        Optional<User> disabledUser = userRepository.findByEmail(dto.getEmail());
        User newUser;
        if (disabledUser.isPresent()){
            newUser = disabledUser.get();
            newUser.setUsername(dto.getUsername());
            newUser.setPassword(dto.getPassword());
            newUser.setIsDisabled(false);
        }
        else {
            newUser = userConversionService.UserDto2UserEntity(dto);
        }
        User savedUser = userRepository.save(newUser);
        return userConversionService.UserEntity2UserDto(savedUser);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(user -> userConversionService.UserEntity2UserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(final Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
        return userConversionService.UserEntity2UserDto(user);
    }

    @Override
    public UserDto update(final Long id, final UserDto dto) {
        userDtoValidator.validate(dto);
        User oldUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id %d not found", id)));
        User newUser = setUserForUpdate(oldUser, userConversionService.UserDto2UserEntity(dto));
        userRepository.save(newUser);
        return userConversionService.UserEntity2UserDto(newUser);
    }

    public void forgetPassword(final Long id, final ForgetPasswordDto forgetPasswordDto) {
        forgetPasswordValidator.validate(forgetPasswordDto);
        Optional<User> user = userRepository.findByIdAndAndEmailAndReminder(id, forgetPasswordDto.getEmail(), forgetPasswordDto.getReminder());
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with these data not found");
        }
        user.get().setPassword(forgetPasswordDto.getNewPassword());
        userRepository.save(user.get());
    }

    @Override
    public void delete(final Long id) {
        User user = userRepository.findByIdAndIsDisabledFalse(id).orElseThrow(()
                -> new UserNotFoundException(String.format("User with id %d not found", id)));
        user.setIsDisabled(true);
        userRepository.save(user);
    }


    private User setUserForUpdate(final User oldUser, final User newUser){
        oldUser.setUsername(newUser.getUsername());
        oldUser.setReminder(newUser.getReminder());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPassword(newUser.getPassword());
        return oldUser;
    }
}
