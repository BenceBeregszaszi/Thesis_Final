package ekke.spring.service.authentication;

import ekke.spring.common.Enum.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUser implements JwtUser, Principal {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getSubject() {
        return id.toString();
    }

    @Override
    public String getName() {
        return id.toString();
    }
}
