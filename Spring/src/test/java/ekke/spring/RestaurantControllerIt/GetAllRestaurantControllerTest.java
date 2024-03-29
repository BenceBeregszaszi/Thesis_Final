package ekke.spring.RestaurantControllerIt;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetAllRestaurantControllerTest extends RestaurantIt {

    private static final String URL = "/restaurants";

    @Test
    @SneakyThrows
    public void getAllRestaurantThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(URL);
        //THEN
        isOk(resultActions);
    }
}
