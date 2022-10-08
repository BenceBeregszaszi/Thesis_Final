package ekke.spring.RestaurantControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class DeleteRestaurantControllerTest extends RestaurantIt {

    private static final String URL = "/restaurants/%d";

    private static final int EXISTING_ID = 6;

    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteRestaurantWithCorrectIdThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteRestaurantWithNoIdFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteRestaurantWithNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void deleteRestaurantWithWrongAuthority() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, EXISTING_ID));
        //THEN
        hasNoAccess(resultActions);
    }
}
