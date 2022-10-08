package ekke.spring.CityControllerIt;

import ekke.spring.TestUtils.CityTestUtil.CityTestUtil;
import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class UpdateCityControllerTest extends CityIt {

    private static final String URL = "/cities/%d";

    private static final int EXISTING_ID = 5;

    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateCityThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID), CityTestUtil.getTestCityDto("8765", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateCityNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, null), CityTestUtil.getTestCityDto("8765", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateCityNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateCityPostCodeAlreadyExists() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID), CityTestUtil.getTestCityDto("3300", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        isConflict(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void updateCityIdNotFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, NOT_EXISTING_ID), CityTestUtil.getTestCityDto("8765", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void updateCityWithWrongAuthorityThenReceiveForbidden() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID), CityTestUtil.getTestCityDto("8765", "Kaposvar", "34.98", "45.267", "[5,7]"));
        //THEN
        hasNoAccess(resultActions);
    }
}
