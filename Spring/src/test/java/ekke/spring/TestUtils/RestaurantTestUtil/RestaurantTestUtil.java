package ekke.spring.TestUtils.RestaurantTestUtil;

import ekke.spring.dto.RestaurantDto;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

public class RestaurantTestUtil {

    public static final String getTestRestaurantDto(final String name, final String maxSeatsNumber, final String cities) {
        return "{\"name\":" + "\"" + name + "\"" +",\"maxSeatsNumber\":" + "\"" + maxSeatsNumber + "\"" +",\"cities\":" + "\"" + cities + "\"" + "}";
    }
}
