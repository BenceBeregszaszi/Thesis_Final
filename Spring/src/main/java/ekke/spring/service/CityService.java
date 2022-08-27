package ekke.spring.service;

import ekke.spring.common.CrudServices;
import ekke.spring.common.IdValidator;
import ekke.spring.conversion.CityConversionService;
import ekke.spring.dao.entity.City;
import ekke.spring.dao.repository.CityRepository;
import ekke.spring.dto.CityDto;
import ekke.spring.service.exception.CityNotFoundException;
import ekke.spring.validators.CityDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService implements CrudServices<CityDto> {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityConversionService cityConversionService;

    @Autowired
    private CityDtoValidator cityDtoValidator;

    @Autowired
    private IdValidator idValidator;

    @Override
    public CityDto add(final CityDto dto) {
        cityDtoValidator.validate(dto);
        City newCity = cityConversionService.CityDto2CityEntity(dto);
        cityRepository.save(newCity);
        return cityConversionService.CityEntity2CityDto(newCity);
    }

    @Override
    public List<CityDto> getAll() {
        List<City> allCity = cityRepository.findAll();
        return cityConversionService.cityEntityList2CityDtoList(allCity);
    }

    @Override
    public CityDto getById(final Long id) {
        City result = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", id)));
        return cityConversionService.CityEntity2CityDto(result);
    }

    @Override
    public CityDto update(final Long id, final CityDto dto) {
        idValidator.validateId(id);
        cityDtoValidator.validate(dto);
        City oldCity = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", id)));
        City newCity = cityConversionService.CityDto2CityEntity(dto);
        oldCity.setPostCode(newCity.getPostCode());
        return cityConversionService.CityEntity2CityDto(oldCity);
    }

    @Override
    public void delete(final Long id) {
        idValidator.validateId(id);
        cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", id)));
        cityRepository.deleteById(id);
    }
}
