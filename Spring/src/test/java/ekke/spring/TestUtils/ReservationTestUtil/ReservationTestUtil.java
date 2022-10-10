package ekke.spring.TestUtils.ReservationTestUtil;


public class ReservationTestUtil {

    public static final String getTestReservationDto(final String userId, final String seatNumber, final String cityId, final String restaurantId, final String time) {
        return "{" + "\"userId\":" + "\"" + userId + "\"" + ", \"seatNumber\":" + "\"" +  seatNumber
                + "\"" + ", \"cityId\":" + "\"" + cityId + "\"" + ", \"restaurantId\":" + "\"" + restaurantId + "\"" + ", \"time\":" + "\"" + time + "\"}";
    }
}
