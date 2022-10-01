package ekke.spring.configuration.Filter;

import ekke.spring.service.authentication.JwtService;
import ekke.spring.validators.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtFilterFactory {

    @Autowired
    private AuthorizationHeaderValidator authorizationHeaderValidator;

    @Autowired
    private JwtValidator jwtValidator;

    @Autowired
    private JwtService jwtService;

    public JwtFilter create() {
        return new JwtFilter(authorizationHeaderValidator, jwtValidator, jwtService);
    }
}
