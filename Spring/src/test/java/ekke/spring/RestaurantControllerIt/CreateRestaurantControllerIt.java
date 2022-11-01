package ekke.spring.RestaurantControllerIt;

import ekke.spring.TestUtils.RestaurantTestUtil.RestaurantTestUtil;
import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class CreateRestaurantControllerIt extends RestaurantIt {

    private static final String URL = "/restaurants";

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createRestaurantThenReceiveOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, RestaurantTestUtil.getTestRestaurantDto("Horgasz palota", "20", "[5,6]", "Kiss Endre utca 34"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void createRestaurantWithWrongAuthorityThenHasNoAccess(){
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, RestaurantTestUtil.getTestRestaurantDto("Placc", "40", "[5,6]", "Kiss Endre utca 34"));
        //THEN
        hasNoAccess(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createRestaurantWithNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createRestaurantWithAlreadyExistingThenConflict() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, RestaurantTestUtil.getTestRestaurantDto("Heaven", "40", "[5,6]", "Kiss Endre utca 34"));
        //THEN
        isConflict(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createRestaurantWithEmptyNameThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, RestaurantTestUtil.getTestRestaurantDto("", "40", "[5,6]", "Kiss Endre utca 34"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createRestaurantWithEmptyAddressThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, RestaurantTestUtil.getTestRestaurantDto("", "40", "[5,6]", ""));
        //THEN
        isBadRequest(resultActions);
    }
}
