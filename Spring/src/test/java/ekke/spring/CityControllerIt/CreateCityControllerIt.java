package ekke.spring.CityControllerIt;


import ekke.spring.TestUtils.CityTestUtil.CityTestUtil;
import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import ekke.spring.dto.CityDto;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class CreateCityControllerIt extends CityIt {

    //mock
    private static final String URL = "/cities";

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, CityTestUtil.getTestCityCreatorDto("8765", "Kaposvar", "34.98", "45.267"));
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
                post(URL, CityTestUtil.getTestCityCreatorDto("8765", "Kaposvar", "34.98", "45.267"));
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
                post(URL, CityTestUtil.getTestCityCreatorDto("3300", "Kaposvar", "34.98", "45.267"));
        //THEN
        isConflict(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void createCityWithPostCodeNullThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, CityTestUtil.getTestCityCreatorDto(null, "Kaposvar", "34.98", "45.267"));
        //THEN
        isBadRequest(resultActions);
    }
}
