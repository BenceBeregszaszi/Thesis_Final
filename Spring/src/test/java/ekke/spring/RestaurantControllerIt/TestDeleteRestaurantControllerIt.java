package ekke.spring.RestaurantControllerIt;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestDeleteRestaurantControllerIt extends TestRestaurantIt {

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
