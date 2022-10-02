package ekke.spring.service.authentication;

import ekke.spring.dao.entity.User;
import ekke.spring.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;

public class AuthManager implements AuthenticationManager {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Optional<User> user = userRepository.findByUsernameAndPassword(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        if (user.isEmpty()) {
            throw new ekke.spring.service.exception.AuthenticationException("User cannot be found!");
        }
        return authentication;
    }
}
