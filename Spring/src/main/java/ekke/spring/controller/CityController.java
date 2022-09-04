package ekke.spring.controller;

import ekke.spring.dto.CityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController extends ControllerBase{

    @PostMapping("/cities")
    public ResponseEntity<CityDto> createCity(@RequestBody final CityDto cityDto){
        return null;
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> readCities(){
        return null;
    }

    @PutMapping("/cities/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable final Long id, @RequestBody final CityDto cityDto){
        return null;
    }


    @DeleteMapping("/cities/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable final Long id){
        return null;
    }
}
