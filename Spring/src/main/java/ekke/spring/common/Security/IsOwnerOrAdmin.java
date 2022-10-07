package ekke.spring.common.Security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IsOwnerOrAdmin {
}
