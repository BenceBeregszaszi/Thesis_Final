package ekke.spring.validators;

import ekke.spring.common.BaseValidator;
import ekke.spring.service.authentication.JwtService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class JwtValidator extends BaseValidator {

    @Autowired
    private JwtService jwtService;

    private Set<String> invalidatedTokens = new HashSet<String>();

    public void validate(final String token) {
        if (token == null || token.isEmpty())
            throw new JwtException("Invalid jwt: is null or empty");

        if (jwtService.isTokenExpired(token))
            throw new JwtException("Invalid jwt: expired");

        if (invalidatedTokens.contains(token))
            throw new JwtException("Invalid jwt: invalidated");
    }

    public void invalidateToken(final String token) {
        invalidatedTokens.removeIf(invalidToken -> jwtService.isTokenExpired(invalidToken));
        if (!jwtService.isTokenExpired(token)){
            invalidatedTokens.add(token);
        }
    }
}
