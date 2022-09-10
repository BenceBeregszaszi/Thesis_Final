package ekke.spring.UserControllerIt;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestDeleteUserControllerIt extends TestUserIt {

    @Test
    @SneakyThrows
    public void deleteRestaurantWithCorrectIdThenReceiveOk() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void deleteRestaurantWithNoIdFoundThenNotFound() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void deleteRestaurantWithNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void deleteRestaurantWithWrongAuthority() {
        //GIVEN
        //WHEN
        //THEN
    }
}
