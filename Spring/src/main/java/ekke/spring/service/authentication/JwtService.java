package ekke.spring.service.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import ekke.spring.configuration.JwtProperties;
import ekke.spring.dto.UserDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    enum TokenType {
        ACCESS,
        REFRESH
    }

    @Autowired
    private JwtProperties jwtProperties;
    private static final String USER_KEY_IN_JSON = "user";
    private Clock clock = DefaultClock.INSTANCE;
    private ObjectMapper objectMapper = new ObjectMapper();

    public String generateUserToken(final TokenType tokenType, final JwtUser user) {
        if (Objects.isNull(user)){
            throw new NullPointerException();
        }
        final Map<String, Object> claims = new HashMap<>();
        claims.put(USER_KEY_IN_JSON, user);

        JwtBuilder baseJwt = Jwts.builder().setClaims(claims).setSubject(user.getSubject());
        return finalizeJwt(baseJwt, getExpirationTime(tokenType));
    }

    public String refreshToken(final TokenType type, final String token) {
        JwtBuilder baseJwt = Jwts.builder().setClaims(getClaims(token));
        return finalizeJwt(baseJwt, getExpirationTime(type));
    }

    public String getSubject(final String token){
        return getClaims(token).toString();
    }

    public <T extends JwtUser> T getUser(final String token, final Class<T> userType) {
        return objectMapper.convertValue(getClaims(token).get(USER_KEY_IN_JSON), userType);
    }

    public boolean isTokenExpired(final String token) {
        try {
            getClaims(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Claims getClaims(final String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody();
    }

    private Long getExpirationTime(final TokenType type){
        switch (type) {
            case ACCESS:
                return jwtProperties.getAccessTokenExpirationTime();
            case REFRESH:
                return jwtProperties.getRefreshTokenExpirationTime();
            default:
                throw new IllegalArgumentException(String.format("Unknown UserTokenTye: %s", type.toString()));
        }
    }

    private String finalizeJwt(final JwtBuilder jwtBuilder, final Long expirationTimeInSec) {
        Date issueDate = clock.now();
        Date expireDate = new Date(issueDate.getTime() + expirationTimeInSec * 1000);
        return jwtBuilder
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(issueDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }
}
