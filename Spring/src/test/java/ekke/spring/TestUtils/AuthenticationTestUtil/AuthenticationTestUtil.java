package ekke.spring.TestUtils.AuthenticationTestUtil;

public class AuthenticationTestUtil {

    public static final String getAuthenticationDto(final String username, final String password) {
        return "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
    }
}
