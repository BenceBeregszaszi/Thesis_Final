package ekke.spring.UserControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.TestUsers.TestUserGuest;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class DeleteUserControllerTest extends UserIt {

    private static final String URL = "/users/%d";

    private static final int EXISTING_ID = 5;

    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteUserWithCorrectIdThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteUserWithNoIdFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteUserWithNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserGuest.class)
    public void deleteUserWithWrongAuthority() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, EXISTING_ID));
        //THEN
        hasNoAccess(resultActions);
    }
}
