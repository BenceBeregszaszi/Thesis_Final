package ekke.spring.UserControllerIt;


import ekke.spring.TestUtils.ForgetPasswordTestUtil.ForgetPasswordTestUtil;
import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserGuest;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;


public class ForgetPasswordUserControllerTest extends UserIt {

    private static final String URL = "/users/forget-password";

    private static final Long EXISTING_ID = 5L;
    private static final Long NOT_EXISTING_ID = 100L;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void testUserForgetPasswordOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = put(URL, ForgetPasswordTestUtil.getForgetPasswordDto("admin reminder", "admin123", "admin@gmail.com"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserGuest.class)
    public void testUserForgetPasswordWithWrongAuthority() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), ForgetPasswordTestUtil.getForgetPasswordDto("admin reminder", "admin123", "admin@gmail.com"));
        //THEN
        hasNoAccess(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void testUserNotFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, NOT_EXISTING_ID), ForgetPasswordTestUtil.getForgetPasswordDto("asdfgh", "123456admin", "admin@gmail.com"));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void testUserForgetPasswordWithEmptyReminder() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), ForgetPasswordTestUtil.getForgetPasswordDto("", "1234kjdsf", "admin@gmail.com"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void testUserForgetPasswordWithEmptyNewPassword() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), ForgetPasswordTestUtil.getForgetPasswordDto("admin reminder", "", "admin@gmail.com"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void testUserForgetPasswordWithEmptyEmail() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), ForgetPasswordTestUtil.getForgetPasswordDto("admin reminder", "oiwer", ""));
        //THEN
        isBadRequest(resultActions);
    }
}
