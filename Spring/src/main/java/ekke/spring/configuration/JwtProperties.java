package ekke.spring.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:jwt.properties")
public class JwtProperties {
    private String secret;
    private String issuer;
    private Long accessTokenExpirationTime;
    private Long refreshTokenExpirationTime;
}
