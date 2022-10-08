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
        ResultActions resultActions = post(URL, AuthenticationTestUtil.getAuthenticationDto("user", "user"));
        //THEN
        isOk(resultActions);
    }
}
