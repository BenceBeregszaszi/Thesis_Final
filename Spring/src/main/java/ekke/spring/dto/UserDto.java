package ekke.spring.dto;

import ekke.spring.common.Authority;
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

    private Authority authority;
}
