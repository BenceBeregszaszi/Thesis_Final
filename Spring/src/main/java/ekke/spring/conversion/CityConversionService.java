package ekke.spring.conversion;

import ekke.spring.dao.entity.City;
import ekke.spring.dto.CityDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityConversionService {

    public CityDto CityEntity2CityDto(final City city){
        CityDto cityDto = new CityDto();
        return cityDto;
    }

    public City CityDto2CityEntity(final CityDto cityDto){
        City city = new City();
        return  city;
    }

    public List<CityDto> cityEntityList2CityDtoList(final List<City> cities){
        List<CityDto> cityDtoList = new ArrayList<>();
        for (City city : cities){
            cityDtoList.add(this.CityEntity2CityDto(city));
        }
        return cityDtoList;
    }
}
