package ekke.spring.conversion;

import ekke.spring.dao.entity.City;
import ekke.spring.dto.CityDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityConversionService {

    public CityDto CityEntity2CityDto(final City city){
        CityDto cityDto = new CityDto();
        return cityDto;
    }

    public City CityDto2CityEntity(final CityDto cityDto){
        City city = new City();
        return  city;
    }
}
