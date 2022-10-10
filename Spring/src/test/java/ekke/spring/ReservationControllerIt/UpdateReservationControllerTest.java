package ekke.spring.ReservationControllerIt;

import ekke.spring.TestUtils.ReservationTestUtil.ReservationTestUtil;
import ekke.spring.TestUtils.TestUsers.TestUserAdam;
import ekke.spring.TestUtils.TestUsers.TestUserGuest;
import ekke.spring.TestUtils.WithTestUser;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

public class UpdateReservationControllerTest extends ReservationIt {

    private static final String URL = "/reservations/%d";

    private static final int EXISTING_ID  = 5;
    private static final int NOT_EXISTING_ID  = 100;

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void updateReservationThenReceiveOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID),
                        ReservationTestUtil.getTestReservationDto("5", "10", "7", "7", "2022-01-04"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void updateReservationNullIdThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, null),
                        ReservationTestUtil.getTestReservationDto("5", "10", "7", "7", "2022-01-04"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void updateReservationNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = put(String.format(URL, EXISTING_ID), null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void updateReservationIdNotFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, NOT_EXISTING_ID),
                        ReservationTestUtil.getTestReservationDto("5", "10", "7", "7", "2022-01-04"));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserGuest.class)
    public void updateReservationWrongAuthorityFoundThenNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID), ReservationTestUtil.getTestReservationDto("5", "10", "7", "7", "2022-01-04"));
        //THEN
        hasNoAccess(resultActions);
    }

    @Test
    @SneakyThrows
    @WithTestUser(user = TestUserAdam.class)
    public void testUpdateReservationSeatNumberThenReceivesConflict() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                put(String.format(URL, EXISTING_ID), ReservationTestUtil.getTestReservationDto("5", "100", "7", "8", "2020-10-01"));
        //THEN
        isAcceptable(resultActions);
    }
}
