package ekke.spring.RestaurantControllerIt;

import ekke.spring.TestUtils.RestaurantTestUtil.RestaurantTestUtil;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class UpdateRestaurantControllerTest extends RestaurantIt {

    private static final String URL = "/restaurants/%d";

    private static final int EXISTING_ID = 5;

    private static final int NOT_EXISTING_ID = 100;


    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateRestaurantThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), RestaurantTestUtil.getTestRestaurantDto("Placc", "40", "[5,6]", "Kiss Endre utca 34"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateRestaurantNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, null), RestaurantTestUtil.getTestRestaurantDto("Placc", "40", "[5,6]", "Kiss Endre utca 34"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateRestaurantNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateRestaurantIdNotFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, NOT_EXISTING_ID), RestaurantTestUtil.getTestRestaurantDto("Placc", "40", "[5,6]", "Kiss Endre utca 34"));
        //THEN
        isNotFound(resultActions);
    }
}
