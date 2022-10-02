package ekke.spring.common.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ADMIN,
    USER,
    NON_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
