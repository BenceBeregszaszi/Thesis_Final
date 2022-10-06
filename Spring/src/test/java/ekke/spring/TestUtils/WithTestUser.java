package ekke.spring.TestUtils;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithTestUserSecurityContextFactory.class)
public @interface WithTestUser {
    Class<? extends TestUser> user();
}
