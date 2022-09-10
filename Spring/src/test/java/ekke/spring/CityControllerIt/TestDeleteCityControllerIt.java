package ekke.spring.CityControllerIt;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestDeleteCityControllerIt extends TestCityIt {

    @Test
    @SneakyThrows
    public void deleteCityWithCorrectIdThenReceiveOk() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void deleteCityWithNoIdFoundThenNotFound() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void deleteCityWithNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void deleteCityWithWrongAuthority() {
        //GIVEN
        //WHEN
        //THEN
    }
}
