package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.dto.RestaurantDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    public RestaurantDto getById(Long id) {
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
