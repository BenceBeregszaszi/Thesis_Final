package ekke.spring.RestaurantControllerIt;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestCreateRestaurantControllerIt extends TestRestaurantIt {

    @Test
    @SneakyThrows
    public void createRestaurantThenReceiveOk(){
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createRestaurantWithWrongAuthorityThenHasNoAccess(){
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createRestaurantWithNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createRestaurantWithAlreadyExistingThenConflict() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createRestaurantWithPostCodeNullThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }
}
