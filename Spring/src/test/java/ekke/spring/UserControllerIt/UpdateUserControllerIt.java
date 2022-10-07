package ekke.spring.UserControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.TestUsers.TestUserGuest;
import ekke.spring.TestUtils.UserTestUtil.UserTestUtil;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class UpdateUserControllerIt extends UserIt {

    private static final String URL = "/users/%d";

    private static final int EXISTING_ID = 5;

    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateUserThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID), UserTestUtil.getTestUserDto("Aron", "aron", "aron@gmail.com", "user"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateUserNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, null), UserTestUtil.getTestUserDto("Aron", "aron", "aron@gmail.com", "user"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateUserNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateUserIdNotFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, NOT_EXISTING_ID), UserTestUtil.getTestUserDto("Aron", "aron", "aron@gmail.com", "user"));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserGuest.class)
    public void updateUserWrongAuthorityThenForbidden() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID), UserTestUtil.getTestUserDto("Aron", "aron", "aron@gmail.com", "user"));
        //THEN
        hasNoAccess(resultActions);
    }
}
