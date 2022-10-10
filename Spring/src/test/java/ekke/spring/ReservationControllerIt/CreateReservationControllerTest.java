package ekke.spring.ReservationControllerIt;

import ekke.spring.TestUtils.ReservationTestUtil.ReservationTestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;


public class CreateReservationControllerTest extends ReservationIt {

    private static final String URL = "/reservations";

    private static final String NOT_EXISTING_UID = "100";

    @Test
    @SneakyThrows
    public void testCreateReservationCorrectThenReceivesOk() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, ReservationTestUtil.getTestReservationDto("5", "10", "7", "7", "2022-01-04"));
        //THEN
        isOk(resultActions);
    }

    @Test
    @SneakyThrows
    public void testCreateReservationNullBodyThenBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions = post(URL, null);
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    public void testCreateReservationUserNotFoundThenReceivesNotFound() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, ReservationTestUtil.getTestReservationDto(NOT_EXISTING_UID, "10", "7", "7", "2022-01-04"));
        //THEN
        isNotFound(resultActions);
    }

    @Test
    @SneakyThrows
    public void testCreateReservationNullRestaurantIdThenReceivesBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, ReservationTestUtil.getTestReservationDto("5", "10", "7", null, "2022-01-04"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    public void testCreateReservationNullSeatNumberThenReceivesBadRequest() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, ReservationTestUtil.getTestReservationDto("5", null, "7", "7", "2022-01-04"));
        //THEN
        isBadRequest(resultActions);
    }

    @Test
    @SneakyThrows
    public void testCreateReservationTooMuchSeatNumberThenReceivesNotAcceptable() {
        //GIVEN
        //WHEN
        ResultActions resultActions =
                post(URL, ReservationTestUtil.getTestReservationDto("5", "100", "7", "7", "2022-01-04"));
        //THEN
        isAcceptable(resultActions);
    }
}
