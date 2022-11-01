package ekke.spring.UserControllerIt;

import ekke.spring.TestUtils.UserTestUtil.UserTestUtil;
import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class CreateUserControllerTest extends UserIt {

    private static final String URL = "/authentication/register";

    @Test
    @SneakyThrows
    public void createUserThenReceiveOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserDto("Aron", "aron", "aron@gmail.com", "user", "aron reminder"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    public void createUserWithNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    public void createUserWithAlreadyExistingThenConflict() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserDto("admin", "admin", "aron@gmail.com", "user", "admin reminder"));
        //THEN
        isConflict(resultActions);
    }

    @Test
    @SneakyThrows
    public void createNewUserWithEmptyNameThenReceiveOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserDto("", "aron", "aron@gmail.com", "user", "aron reminder"));
        //THEN
        isBadRequest(resultActions);
    }


    @Test
    @SneakyThrows
    public void createNewUserWithEmptyPasswordThenReceiveOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserDto("Aron", "", "aron@gmail.com", "user", "aron reminder"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    public void createNewUserWithEmptyReminderThenReceiveOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, UserTestUtil.getTestUserDto("Aron", "", "aron@gmail.com", "user", ""));
        //THEN
        isBadRequest(resultActions);
    }
}
