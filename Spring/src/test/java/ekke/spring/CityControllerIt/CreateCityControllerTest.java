package ekke.spring.CityControllerIt;


import ekke.spring.TestUtils.CityTestUtil.CityTestUtil;
import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class CreateCityControllerTest extends CityIt {

    //mock
    private static final String URL = "/cities";

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, CityTestUtil.getTestCityDto("8765", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void createCityWithWrongAuthorityThenHasNoAccess() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, CityTestUtil.getTestCityDto("8765", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        hasNoAccess(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityWithNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityWithAlreadyExistingThenConflict() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, CityTestUtil.getTestCityDto("3300", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        isConflict(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityWithPostCodeEmptyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, CityTestUtil.getTestCityDto("", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityWithLongitudeNullCodeEmptyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, CityTestUtil.getTestCityDto("", "Kaposvar", null, "45.267", "[5,7]"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityWithLatitudeNullCodeEmptyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, CityTestUtil.getTestCityDto("", "Kaposvar", "34.98", null, "[5,7]"));
        //THEN
        isBadRequest(resultActions);
    }
}
