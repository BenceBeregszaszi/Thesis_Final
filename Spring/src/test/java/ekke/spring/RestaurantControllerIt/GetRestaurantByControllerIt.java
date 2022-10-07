package ekke.spring.RestaurantControllerIt;

import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetRestaurantByControllerIt extends RestaurantIt {

    private static final String URL = "/restaurants/%d";

    private static final int EXISTING_ID = 7;
    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    public void getRestaurantByIdThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    public void getRestaurantByIdNotPresentThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    public void getRestaurantByIdNotNullThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }
}
