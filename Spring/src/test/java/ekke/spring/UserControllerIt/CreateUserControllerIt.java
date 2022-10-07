package ekke.spring.UserControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.TestUsers.TestUserGuest;
import ekke.spring.TestUtils.WithTestUser;
import ekke.spring.TestUtils.UserTestUtil.UserTestUtil;
import ekke.spring.dto.UserDto;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class CreateUserControllerIt extends UserIt {

    private static final String URL = "/authentication/register";

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createUserThenReceiveOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserCreatorDto("Aron", "aron", "aron@gmail.com", "user"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserGuest.class)
    public void createNewUserThenReceiveOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserCreatorDto("Aron", "aron", "aron@gmail.com", "user"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void createUserWithWrongAuthorityThenHasNoAccess(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserCreatorDto("Aron", "aron", "aron@gmail.com", "user"));
        //THEN
        hasNoAccess(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createUserWithNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createUserWithAlreadyExistingThenConflict() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserCreatorDto("user", "user", "aron@gmail.com", "user"));
        //THEN
        isConflict(resultActions);
    }
}
