package ekke.spring.UserControllerIt;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestCreateUserControllerIt extends TestUserIt {
    @Test
    @SneakyThrows
    public void createUserThenReceiveOk(){
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createUserWithWrongAuthorityThenHasNoAccess(){
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createUserWithNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createUserWithAlreadyExistingThenConflict() {
        //GIVEN
        //WHEN
        //THEN
    }

    @Test
    @SneakyThrows
    public void createUserWithPostCodeNullThenBadRequest() {
        //GIVEN
        //WHEN
        //THEN
    }
}
