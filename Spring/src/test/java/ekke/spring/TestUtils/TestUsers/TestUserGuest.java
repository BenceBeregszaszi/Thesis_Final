package ekke.spring.TestUtils.TestUsers;

import ekke.spring.TestUtils.TestUser;
import ekke.spring.common.Enum.Authority;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class TestUserGuest implements TestUser {

    @Getter
    public static final Long id = 3L;

    @Getter
    public static final String username = "Guest";

    @Getter
    public static final String password = "a";

    @Getter
    public static final Collection<? extends GrantedAuthority> authorities = List.of(Authority.NON_USER);
}
