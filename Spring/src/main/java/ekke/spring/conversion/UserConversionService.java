package ekke.spring.conversion;

import ekke.spring.dao.entity.User;
import ekke.spring.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConversionService {

    public UserDto UserEntity2UserDto(User user){
        UserDto userDto = new UserDto();
        return userDto;
    }

    public User UserDto2UserEntity(UserDto userDto){
        User user = new User();
        return user;
    }
}
