package ekke.spring.CityControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class DeleteCityControllerIt extends CityIt {

    private static final String URL = "/cities/%d";

    private static final int EXISTING_ID = 6;
    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteCityWithCorrectIdThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteCityWithNoIdFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteCityWithNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void deleteCityWithWrongAuthority() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, EXISTING_ID));
        //THEN
        hasNoAccess(resultActions);
    }
}
