package ekke.spring.TestUtils.UserTestUtil;

import ekke.spring.common.Enum.Authority;
import ekke.spring.dto.UserDto;

import java.util.Date;

public class UserTestUtil {

    public static final String getTestUserDto(final String username, final String password, final String email, final String authority) {
        return "{\"username\":" + "\"" + username + "\", \"password\":" + "\""
                + password + "\", \"email\":" + "\"" + email + "\", \"authority\":" + "\"" + authority.toUpperCase() + "\"}";
    }
}
