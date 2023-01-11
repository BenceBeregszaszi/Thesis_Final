package ekke.spring.UserControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetUserByUsernameUserControllerTest extends UserIt {
    private static final String URL = "/users/user/%s";


    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void getUserByUsername(){
        //GIVE
        //WHEN
        ResultActions resultActions = get(String.format(URL, "admin"));
        //THEN
        isOk(resultActions);
    }
}
