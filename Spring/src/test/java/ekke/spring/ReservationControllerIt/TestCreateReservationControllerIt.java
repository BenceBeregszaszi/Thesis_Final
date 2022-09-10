package ekke.spring.ReservationControllerIt;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class TestCreateReservationControllerIt extends TestReservationIt {

        @Test
        @SneakyThrows
        public void createReservationThenReceiveOk(){
            //GIVEN
            //WHEN
            //THEN
        }

        @Test
        @SneakyThrows
        public void createReservationWithWrongAuthorityThenHasNoAccess(){
            //GIVEN
            //WHEN
            //THEN
        }

        @Test
        @SneakyThrows
        public void createReservationWithNullBodyThenBadRequest() {
            //GIVEN
            //WHEN
            //THEN
        }

        @Test
        @SneakyThrows
        public void createReservationWithAlreadyExistingThenConflict() {
            //GIVEN
            //WHEN
            //THEN
        }

        @Test
        @SneakyThrows
        public void createReservationWithPostCodeNullThenBadRequest() {
            //GIVEN
            //WHEN
            //THEN
        }
}
