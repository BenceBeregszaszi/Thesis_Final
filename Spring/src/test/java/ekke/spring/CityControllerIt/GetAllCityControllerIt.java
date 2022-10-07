package ekke.spring.CityControllerIt;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetAllCityControllerIt extends CityIt {

    private static final String URL = "/cities";

    @Test
    @SneakyThrows
    public void getAllCityThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(URL);
        //THEN
        isOk(resultActions);
    }
}
