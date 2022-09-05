package ekke.spring.conversion;

import ekke.spring.dao.entity.User;
import ekke.spring.dto.UserDto;
import org.springframework.stereotype.Component;


@Component
public class UserConversionService {

    public UserDto UserEntity2UserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setVersion(user.getVersion());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        userDto.setAuthority(user.getAuthority());
        return userDto;
    }

    public User UserDto2UserEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setVersion(userDto.getVersion());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAuthority(userDto.getAuthority());
        return user;
    }
}
