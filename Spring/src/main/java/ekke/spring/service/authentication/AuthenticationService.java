package ekke.spring.service.authentication;

import ekke.spring.common.exception.ValidationException;
import ekke.spring.dao.entity.User;
import ekke.spring.dao.repository.UserRepository;
import ekke.spring.dao.specification.UserSpecification;
import ekke.spring.dto.AuthenticationRequestDto;
import ekke.spring.dto.TokenPairDto;
import ekke.spring.service.exception.AuthenticationException;
import ekke.spring.validators.AuthenticationRequestValidator;
import ekke.spring.validators.JwtValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
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

    @Autowired
    private UserRepository userRepository;

    public TokenPairDto authenticate(final AuthenticationRequestDto requestDto){
        authenticationRequestValidator.validate(requestDto);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        UserSpecification specification = new UserSpecification();
        specification.setUsername(auth.getPrincipal().toString());
        specification.setPassword(auth.getCredentials().toString());
        specification.setIsDisabled(false);
        User databaseUser = userRepository.findAll(specification).stream().findFirst().get();
        AuthenticatedUser authenticatedUser = buildAuthenticatedUser(databaseUser, auth);
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

    private AuthenticatedUser buildAuthenticatedUser(final User user, Authentication authentication) {
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setId(user.getId());
        authenticatedUser.setUsername(authentication.getPrincipal().toString());
        authenticatedUser.setPassword(authentication.getCredentials().toString());
        authenticatedUser.setAuthorities(Arrays.asList(user.getAuthority()));
        return authenticatedUser;
    }
}
