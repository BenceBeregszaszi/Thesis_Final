package ekke.spring.TestUtils.CityTestUtil;

import ekke.spring.dto.CityDto;

import java.util.Collections;
import java.util.Date;

public class CityTestUtil {

    public static final String getTestCityCreatorDto(final String postCode, final String cityName, final String longitude, final String latitude) {
        return "{\"postCode\":\"" + postCode + "\", \"cityName\":\"" + cityName + "\", \"longitude\":\"" + longitude + "\", \"latitude\":\"" + latitude + "\"}";
    }

    public static final String getTestCityDto(final String postCode, final String cityName, final String longitude, final String latitude) {
        return "{\"postCode\":\"" + postCode + "\", \"cityName\":\"" + cityName + "\", \"longitude\":\"" + longitude + "\", \"latitude\":\"" + latitude + "\"}";
    }
}