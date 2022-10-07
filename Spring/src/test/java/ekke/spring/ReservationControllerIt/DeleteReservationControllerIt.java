package ekke.spring.ReservationControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class DeleteReservationControllerIt extends ReservationIt {

    private static final String URL = "/cities/%d";

    private static final int EXISTING_ID = 5;

    private static final int NOT_EXISTING_ID = 100;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteReservationWithCorrectIdADMINThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void deleteReservationWithNoIdFoundADMINThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void deleteReservationWithNullIdADMINThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void deleteReservationWithNullIdUSERThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = delete(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }
}
