package ekke.spring.UserControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetAllUserControllerTest extends UserIt {

    private static final String URL = "/users";

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void getAllUserThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(URL);
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void getAllUserWithWrongAuthority() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(URL);
        //THEN
        hasNoAccess(resultActions);
    }
}
