package ekke.spring.TestUsers;

import ekke.spring.common.Authority;
import lombok.Getter;

@Getter
public class TestUserNotExists {

    private static final String username = "not_exists";

    private static final String password = "not_exists";

    private static final String email = "not_exists@gmail.com";

    private static final Authority authority = null;
}
