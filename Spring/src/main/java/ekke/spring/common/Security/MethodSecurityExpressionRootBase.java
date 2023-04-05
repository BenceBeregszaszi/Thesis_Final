package ekke.spring.common.Security;

import ekke.spring.common.Enum.Authority;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;

public class MethodSecurityExpressionRootBase extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    public MethodSecurityExpressionRootBase(Authentication authentication) {
        super(authentication);
    }

    protected boolean hasAuthority(final Authority authority) {
        return hasAuthority(authority.getAuthority());
    }

    protected boolean hasAnyAuthority(final Authority... authorities) {
        List<String> authorityList = Arrays.stream(authorities).map(Authority::getAuthority).toList();
        return hasAnyAuthority(authorityList.toArray(new String[authorityList.size()]));
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }
}
