package ekke.spring.service.authentication;

import ekke.spring.common.exception.ValidationException;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dto.AuthenticationRequestDto;
import ekke.spring.dto.TokenPairDto;
import ekke.spring.dto.UserDto;
import ekke.spring.service.UserService;
import ekke.spring.service.exception.AuthenticationException;
import ekke.spring.validators.AuthenticationRequestValidator;
import ekke.spring.validators.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private AuthenticationRequestValidator authenticationRequestValidator;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private JwtValidator jwtValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenPairDto authenticate(final AuthenticationRequestDto requestDto){
        authenticationRequestValidator.validate(requestDto);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        final String accessToken = jwtService.generateUserToken(JwtService.TokenType.ACCESS, authenticatedUser);
        final String refreshToken = jwtService.generateUserToken(JwtService.TokenType.REFRESH, authenticatedUser);
        return new TokenPairDto(accessToken, refreshToken);
    }

    public TokenPairDto refreshToken(final String token) {
        if (Objects.isNull(token) || token.isEmpty()){
            throw new ValidationException("Token is null or empty");
        }
        try {
            jwtValidator.validate(token);
        } catch (ValidationException e) {
            throw new AuthenticationException("Refresh token invalid");
        }
        final String newAccessToken = jwtService.refreshToken(JwtService.TokenType.ACCESS, token);
        final String newRefreshToken = jwtService.refreshToken(JwtService.TokenType.REFRESH, token);
        jwtValidator.invalidateToken(token);
        return new TokenPairDto(newAccessToken, newRefreshToken);
    }
}
