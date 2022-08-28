package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.common.IdValidator;
import ekke.spring.conversion.RestaurantConversionService;
import ekke.spring.dao.entity.Restaurant;
import ekke.spring.dao.repository.RestaurantRepository;
import ekke.spring.dto.RestaurantDto;
import ekke.spring.service.exception.RestaurantNotFoundException;
import ekke.spring.validators.RestaurantDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantService implements CrudServices<RestaurantDto> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantConversionService restaurantConversionService;

    @Autowired
    private RestaurantDtoValidator restaurantDtoValidator;

    @Autowired
    private IdValidator idValidator;

    @Override
    public RestaurantDto add(final RestaurantDto dto) {
        restaurantDtoValidator.validate(dto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurantConversionService.RestaurantDto2RestaurantEntity(dto));
        return restaurantConversionService.RestaurantEntity2RestaurantDto(savedRestaurant);
    }

    @Override
    public List<RestaurantDto> getAll() {
        return restaurantRepository.findAll().stream()
                .map(restaurant -> restaurantConversionService.RestaurantEntity2RestaurantDto(restaurant)).collect(Collectors.toList());
    }

    @Override
    public RestaurantDto getById(final Long id) {
        idValidator.validateId(id);
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()
                -> new RestaurantNotFoundException(String.format("Restaurant with id %d not found")));
        return restaurantConversionService.RestaurantEntity2RestaurantDto(restaurant);
    }

    @Override
    public RestaurantDto update(final Long id, final RestaurantDto dto) {
        idValidator.validateId(id);
        restaurantDtoValidator.validate(dto);
        restaurantDtoValidator.validateForUpdate(dto.getName(), dto.getMaxSeatsNumber());
        Restaurant oldRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(String.format("Restaurant with id %d not found", id)));
        Restaurant newRestaurant = setRestaurantForUpdate(oldRestaurant, restaurantConversionService.RestaurantDto2RestaurantEntity(dto));
        restaurantRepository.save(newRestaurant);
        return restaurantConversionService.RestaurantEntity2RestaurantDto(newRestaurant);
    }

    @Override
    public void delete(final Long id) {
        idValidator.validateId(id);
        restaurantRepository.findById(id).orElseThrow(()
                -> new RestaurantNotFoundException(String.format("Restaurant with id %d not found", id)));
        restaurantRepository.deleteById(id);
    }

    private Restaurant setRestaurantForUpdate(final Restaurant oldRestaurant, final Restaurant newRestaurant){
        oldRestaurant.setName(newRestaurant.getName());
        oldRestaurant.setMaxSeatsNumber(newRestaurant.getMaxSeatsNumber());
        return oldRestaurant;
    }
}
