package ekke.spring.TestUtils.CityTestUtil;


public class CityTestUtil {

    public static final String getTestCityDto(final String postCode, final String cityName, final String longitude, final String latitude, final String restaurants) {
        return "{\"postCode\":\"" + postCode + "\", \"cityName\":\""
                + cityName + "\", \"longitude\":\"" + longitude + "\", \"latitude\":\"" + latitude + "\", \"restaurants\":" + "\"" + restaurants + "\"" + "}";
    }
}