package ekke.spring.TestUtils;

import ekke.spring.service.authentication.AuthenticatedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.lang.reflect.Method;
import java.util.Collection;

public class WithTestUserSecurityContextFactory implements WithSecurityContextFactory<WithTestUser> {

    @Override
    public SecurityContext createSecurityContext(final WithTestUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        AuthenticatedUser authenticatedUser = mapTestUserToAuthenticatedUser(annotation.user());
        context.setAuthentication(new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities()));
        return context;
    }


    private AuthenticatedUser mapTestUserToAuthenticatedUser(final Class<? extends TestUser> testUserClass) {
        try {

            Long id = (Long) testUserClass.getMethod("getId").invoke(null);
            String username = (String) testUserClass.getMethod("getUsername").invoke(null);
            String password = (String) testUserClass.getMethod("getPassword").invoke((null));
            Collection<? extends GrantedAuthority> authorities =
                    (Collection<? extends GrantedAuthority>) testUserClass.getMethod("getAuthorities").invoke(null);

            AuthenticatedUser authenticatedUser = new AuthenticatedUser();
            authenticatedUser.setId(id);
            authenticatedUser.setUsername(username);
            authenticatedUser.setPassword(password);
            authenticatedUser.setAuthorities(authorities);
            return authenticatedUser;
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
