package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.dto.CityDto;

import java.util.List;

public class CityService implements CrudServices<CityDto> {

    @Override
    public CityDto add(CityDto dto) {
        return null;
    }

    @Override
    public List<CityDto> getAll() {
        return null;
    }

    @Override
    public CityDto update(Long id, CityDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
