package ekke.spring.TestUsers;

import ekke.spring.common.Authority;
import lombok.Getter;

@Getter
public class TestUserUser {

    private static final String username = "User";

    private static final String password = "user";

    private static final String email = "user@gmail.com";

    private static final Authority authority = Authority.USER;
}
