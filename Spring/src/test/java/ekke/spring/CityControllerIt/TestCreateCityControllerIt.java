package ekke.spring.CityControllerIt;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestCreateCityControllerIt extends TestCityIt {

    @Test
    @SneakyThrows
    public void createCityThenReceiveOk() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createCityWithWrongAuthorityThenHasNoAccess() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createCityWithNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createCityWithAlreadyExistingThenConflict() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createCityWithPostCodeNullThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }
}
