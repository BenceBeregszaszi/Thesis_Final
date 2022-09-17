package ekke.spring.conversion;

import ekke.spring.dao.entity.City;
import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.repository.CityRepository;
import ekke.spring.dto.RestaurantDto;
import ekke.spring.service.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


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
        return cities.stream().map(city -> city.getId()).collect(Collectors.toSet());
    }

    private Set<City> long2CityEntity(final Set<Long> cityIds){
        Set<City> cities = new HashSet<>();
        cityIds.forEach(id -> {
            City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", id)));
            cities.add(city);
        });
        return cities;
    }
}
