package ekke.spring.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ekke.spring.common.Enum.Authority;
import ekke.spring.conversion.AuthorityDeserializer;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private Date version;

    private String username;

    private String password;

    private String email;

    @JsonDeserialize(using = AuthorityDeserializer.class)
    private Authority authority;

    private String reminder;

    private Boolean isDisabled;
}
