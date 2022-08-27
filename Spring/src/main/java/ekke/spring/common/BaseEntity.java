package ekke.spring.common;

import javax.persistence.*;
import java.util.Date;

@Entity
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Date version;
}
