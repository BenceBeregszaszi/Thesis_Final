package ekke.spring.ReservationControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetAllReservationControllerIt extends ReservationIt {

    private static final String URL = "/reservations";

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void getAllReservationThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(URL);
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void getAllReservationWithWrongAuthorityThenReceivesBadRequest(){
        //GIVEN
        //WHEN
        ResultActions resultActions = get(URL);
        //THEN
        hasNoAccess(resultActions);
    }
}
