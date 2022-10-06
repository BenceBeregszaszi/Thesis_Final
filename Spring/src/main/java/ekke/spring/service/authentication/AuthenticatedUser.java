package ekke.spring.service.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ekke.spring.common.Enum.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUser implements JwtUser, Principal {

    private Long id;
    private String username;
    private String password;

    @JsonDeserialize(contentAs = Authority.class)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getSubject() {
        return id.toString();
    }

    @Override
    @JsonIgnore
    public String getName() {
        return id.toString();
    }
}
