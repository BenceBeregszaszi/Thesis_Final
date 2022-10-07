package ekke.spring.ReservationControllerIt;

import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserGuest;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetReservationByIdControllerIt extends ReservationIt {

    private static final String URL = "/reservations/%d";

    private static final int EXISTING_ID = 1;

    private static final int NOT_EXISTING_ID = 100;


    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void getReservationByIdThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void getReservationByIdNotPresentThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void getReservationByIdNullThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserGuest.class)
    public void getReservationsWrongAuthorityThenReceiveForbidden() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, EXISTING_ID));
        //THEN
        hasNoAccess(resultActions);
    }
}
