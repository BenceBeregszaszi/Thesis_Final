package ekke.spring.dao.entity;

import ekke.spring.common.Enum.Authority;
import ekke.spring.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, length = 200)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 200)
    private String email;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "reminder", nullable = false, length = 255)
    private String reminder;

    @Column(nullable = false)
    private Boolean isDisabled;
}
