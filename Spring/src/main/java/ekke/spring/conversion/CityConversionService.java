package ekke.spring.conversion;

import ekke.spring.dao.entity.City;
import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.repository.RestaurantRepository;
import ekke.spring.dto.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@Component
public class CityConversionService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public CityDto CityEntity2CityDto(final City city){
        CityDto cityDto = new CityDto();
        cityDto.setId(city.getId());
        cityDto.setVersion(city.getVersion());
        cityDto.setPostCode(city.getPostCode());
        cityDto.setRestaurants(mapRestaurant2Long(city.getRestaurants()));
        return cityDto;
    }

    public City CityDto2CityEntity(final CityDto cityDto){
        City city = new City();
        city.setId(cityDto.getId());
        city.setVersion(cityDto.getVersion());
        city.setPostCode(cityDto.getPostCode());
        city.setRestaurants(mapLong2Restaurant(cityDto.getRestaurants()));
        return  city;
    }

    private Set<Long> mapRestaurant2Long(final Set<Restaurant> restaurants) {
        Set<Long> restaurantIds = new HashSet<>();
        restaurants.forEach(restaurant -> restaurantIds.add(restaurant.getId()));
        return restaurantIds;
    }

    private Set<Restaurant> mapLong2Restaurant(final Set<Long> restaurantIds){
        Set<Restaurant> restaurants = new HashSet<>();
        restaurantIds.forEach(id -> restaurants.add(restaurantRepository.findById(id).get()));
        return restaurants;
    }
}
