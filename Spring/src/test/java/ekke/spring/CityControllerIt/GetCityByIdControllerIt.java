package ekke.spring.CityControllerIt;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetCityByIdControllerIt extends CityIt {

    private static final String URL = "/cities/%d";

    private static final int EXISTING_ID = 5;
    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    public void getCityByIdThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    public void getCityByIdNotPresentThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    public void getCityByIdNotNullThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }
}
