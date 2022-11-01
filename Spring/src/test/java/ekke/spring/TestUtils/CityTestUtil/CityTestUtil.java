package ekke.spring.TestUtils.CityTestUtil;


public class CityTestUtil {

    public static final String getTestCityDto(final String postCode, final String cityName, final String restaurants) {
        return "{\"postCode\":\"" + postCode + "\", \"cityName\":\""
                + cityName + "\", \"restaurants\":" + "\"" + restaurants + "\"" + "}";
    }
}