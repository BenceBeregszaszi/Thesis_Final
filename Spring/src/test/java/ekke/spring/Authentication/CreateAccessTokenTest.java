package ekke.spring.Authentication;

import ekke.spring.TestUtils.AuthenticationTestUtil.AuthenticationTestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class CreateAccessTokenTest extends AuthenticationIt {

    private static final String URL = "/authentication/authenticate";

    @Test
    @SneakyThrows
    public void testAuthenticateThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, AuthenticationTestUtil.getAuthenticationDto("bandi1", "bandi"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    public void testAuthenticateEmptyNameThenReceiveBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, AuthenticationTestUtil.getAuthenticationDto("", "user"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    public void testAuthenticateEmptyPasswordThenReceiveBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, AuthenticationTestUtil.getAuthenticationDto("user", ""));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    public void testAuthenticateAuthenticationException() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, AuthenticationTestUtil.getAuthenticationDto("valaki", "valaki"));
        //THEN
        isUnauthorized(resultActions);
    }
}
