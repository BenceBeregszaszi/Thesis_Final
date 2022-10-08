package ekke.spring.TestUtils.TestUsers;

import ekke.spring.TestUtils.TestUser;
import ekke.spring.common.Enum.Authority;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class TestUserAndras implements TestUser {

    @Getter
    public static final Long id = 1L;

    @Getter
    public static final String username = "Andras";

    @Getter
    public static final String password = "a";

    @Getter
    public static final Collection<? extends GrantedAuthority> authorities = Arrays.asList(Authority.ADMIN);
}
