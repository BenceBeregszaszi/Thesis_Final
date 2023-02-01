package ekke.spring.service.authentication;

import ekke.spring.dao.entity.User;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dao.specification.UserSpecification;
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

        UserSpecification specification = new UserSpecification();
        specification.setUsername(authentication.getPrincipal().toString());
        specification.setPassword(authentication.getCredentials().toString());
        specification.setIsDisabled(false);
        Optional<User> user = userRepository.findAll(specification).stream().findFirst();
        if (user.isEmpty() || user.get().getIsDisabled()) {
            throw new ekke.spring.service.exception.AuthenticationException("User cannot be found!");
        }
        return authentication;
    }
}
