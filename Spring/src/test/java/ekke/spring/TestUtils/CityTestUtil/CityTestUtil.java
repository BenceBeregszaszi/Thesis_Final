package ekke.spring.TestUtils.CityTestUtil;

import ekke.spring.dto.CityDto;

import java.util.Collections;
import java.util.Date;

public class CityTestUtil {

    public static final String getTestCityDto(final String postCode, final String cityName, final String longitude, final String latitude, final String restaurants) {
        return "{\"postCode\":\"" + postCode + "\", \"cityName\":\""
                + cityName + "\", \"longitude\":\"" + longitude + "\", \"latitude\":\"" + latitude + "\", \"restaurants\":" + "\"" + restaurants + "\"" + "}";
    }
}