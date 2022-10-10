package ekke.spring.TestUtils.RestaurantTestUtil;


public class RestaurantTestUtil {

    public static final String getTestRestaurantDto(final String name, final String maxSeatsNumber, final String cities) {
        return "{\"name\":" + "\"" + name + "\"" +",\"maxSeatsNumber\":" + "\"" + maxSeatsNumber + "\"" +",\"cities\":" + "\"" + cities + "\"" + "}";
    }
}
