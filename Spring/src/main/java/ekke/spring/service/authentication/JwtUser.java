package ekke.spring.service.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface JwtUser {
    @JsonIgnore
    String getSubject();
}
