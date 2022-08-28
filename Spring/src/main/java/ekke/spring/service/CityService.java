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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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
        City savedCityDto = cityRepository.save(cityConversionService.CityDto2CityEntity(dto));
        return cityConversionService.CityEntity2CityDto(savedCityDto);
    }

    @Override
    public List<CityDto> getAll() {
        return cityRepository.findAll().stream().map((city -> cityConversionService.CityEntity2CityDto(city))).collect(Collectors.toList());
    }

    @Override
    public CityDto getById(final Long id) {
        idValidator.validateId(id);
        City result = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", id)));
        return cityConversionService.CityEntity2CityDto(result);
    }

    @Override
    public CityDto update(final Long id, final CityDto dto) {
        idValidator.validateId(id);
        cityDtoValidator.validate(dto);
        cityDtoValidator.validateForUpdate(dto.getPostCode());
        City oldCity = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", id)));
        City newCity = setCityForUpdate(oldCity, cityConversionService.CityDto2CityEntity(dto));
        cityRepository.save(newCity);
        return cityConversionService.CityEntity2CityDto(newCity);
    }

    @Override
    public void delete(final Long id) {
        idValidator.validateId(id);
        cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(String.format("City with id %d not found", id)));
        cityRepository.deleteById(id);
    }

    private City setCityForUpdate(final City oldCity, final City newCity){
        oldCity.setPostCode(newCity.getPostCode());
        return oldCity;
    }
}
