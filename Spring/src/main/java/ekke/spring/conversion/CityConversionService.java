package ekke.spring.conversion;

import ekke.spring.dao.entity.City;
import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.repository.RestaurantRepository;
import ekke.spring.dto.CityDto;
import ekke.spring.service.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Component
public class CityConversionService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public CityDto CityEntity2CityDto(final City city) {
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setVersion(city.getVersion());
        cityDto.setPostCode(city.getPostCode());
        cityDto.setCityName(city.getCityName());
        cityDto.setRestaurants(mapRestaurant2Long(city.getRestaurants()));
        cityDto.setLongitude(city.getLongitude());
        cityDto.setLatitude(city.getLatitude());
        return cityDto;
    }

    public City CityDto2CityEntity(final CityDto cityDto) {
        City city = new City();
        city.setId(cityDto.getId());
        city.setVersion(cityDto.getVersion());
        city.setPostCode(cityDto.getPostCode());
        city.setCityName(cityDto.getCityName());
        city.setRestaurants(mapLong2Restaurant(cityDto.getRestaurants()));
        city.setLongitude(cityDto.getLongitude());
        city.setLatitude(cityDto.getLatitude());
        return  city;
    }

    private Set<Long> mapRestaurant2Long(final Set<Restaurant> restaurants) {
        Set<Long> restaurantIds = new HashSet<>();
        restaurants.forEach(restaurant -> restaurantIds.add(restaurant.getId()));
        return restaurantIds;
    }

    private Set<Restaurant> mapLong2Restaurant(final Set<Long> restaurantIds) {
        Set<Restaurant> restaurants = new HashSet<>();
        if (Objects.isNull(restaurantIds)) return restaurants;
        restaurantIds.forEach(id -> restaurants.add(restaurantRepository.findById(id).orElseThrow(()
                -> new RestaurantNotFoundException(String.format("Restaurant with id %d not found", id)))));
        return restaurants;
    }
}
