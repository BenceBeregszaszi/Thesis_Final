package ekke.spring.conversion;

import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dto.RestaurantDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantConversionService {

    public RestaurantDto RestaurantEntity2RestaurantDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        return restaurantDto;
    }

    public Restaurant RestaurantDto2RestaurantEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        return restaurant;
    }
}