package ekke.spring.TestUtils.UserTestUtil;

public class UserTestUtil {

    public static final String getTestUserDto(final String username, final String password, final String email, final String authority, final String reminder) {
        return "{\"username\":" + "\"" + username + "\", \"password\":" + "\"" + password
                + "\", \"email\":" + "\"" + email + "\", \"authority\":" + "\"" + authority.toUpperCase() + "\", \"reminder\":" + "\"" + reminder + "\"}";
    }
}
