package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.dto.RestaurantDto;

import java.util.List;

public class RestaurantService implements CrudServices<RestaurantDto> {

    @Override
    public RestaurantDto add(RestaurantDto dto) {
        return null;
    }

    @Override
    public List<RestaurantDto> getAll() {
        return null;
    }

    @Override
    public RestaurantDto update(Long id, RestaurantDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
