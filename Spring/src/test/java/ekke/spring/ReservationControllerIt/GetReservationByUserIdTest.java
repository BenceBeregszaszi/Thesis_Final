package ekke.spring.ReservationControllerIt;


import ekke.spring.TestUtils.TestUsers.TestUserAndras;
import ekke.spring.TestUtils.TestUsers.TestUserGuest;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class GetReservationByUserIdTest extends ReservationIt {

    private static final String URL = "/%d/reservations";

    private static final int NOT_EXISTING_ID = 100;
    private static final int EXISTING_ID = 7;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void testGivenIdCorrectThenReceivesOk(){
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, EXISTING_ID));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void testGivenWrongIdThenReceivesBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, null));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAndras.class)
    public void testGivenNotExistingIdThenReceivesNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, NOT_EXISTING_ID));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserGuest.class)
    public void testGivenWrongAuthorityThenReceivesForbidden() {
        //GIVEN
        //WHEN
        ResultActions resultActions = get(String.format(URL, NOT_EXISTING_ID));
        //THEN
        hasNoAccess(resultActions);
    }
}
