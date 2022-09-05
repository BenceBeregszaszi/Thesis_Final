package ekke.spring.conversion;

import ekke.spring.dao.entity.City;
import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.repository.CityRepository;
import ekke.spring.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class RestaurantConversionService {

    @Autowired
    private CityRepository cityRepository;

    public RestaurantDto RestaurantEntity2RestaurantDto(Restaurant restaurant) {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setVersion(restaurant.getVersion());
        restaurantDto.setName(restaurant.getName());
        restaurantDto.setCities(cityEntity2Long(restaurant.getCities()));
        restaurantDto.setMaxSeatsNumber(restaurant.getMaxSeatsNumber());
        return restaurantDto;
    }

    public Restaurant RestaurantDto2RestaurantEntity(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDto.getId());
        restaurant.setVersion(restaurantDto.getVersion());
        restaurant.setName(restaurantDto.getName());
        restaurant.setCities(long2CityEntity(restaurantDto.getCities()));
        restaurant.setMaxSeatsNumber(restaurantDto.getMaxSeatsNumber());
        return restaurant;
    }

    private Set<Long> cityEntity2Long(final Set<City> cities){
        Set<Long> cityIds = new HashSet<>();
        cities.forEach(city -> cityIds.add(cityRepository.findById(city.getId()).get().getId()));
        return cityIds;
    }

    private Set<City> long2CityEntity(final Set<Long> cityIds){
        Set<City> cities = new HashSet<>();
        cityIds.forEach(id -> cities.add(cityRepository.findById(id).get()));
        return cities;
    }
}
