package ekke.spring.common.Security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        MethodSecurityExpressionRootBase rootBase = new MethodSecurityExpressionRootBase(authentication);
        rootBase.setPermissionEvaluator(getPermissionEvaluator());
        rootBase.setTrustResolver(this.trustResolver);
        rootBase.setRoleHierarchy(getRoleHierarchy());
        return rootBase;
    }
}
