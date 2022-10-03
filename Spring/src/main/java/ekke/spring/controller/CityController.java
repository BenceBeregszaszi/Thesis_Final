package ekke.spring.controller;

import ekke.spring.dto.CityDto;
import ekke.spring.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController extends ControllerBase {

    @Autowired
    private CityService cityService;

    @PostMapping("/cities")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CityDto> createCity(@RequestBody final CityDto cityDto) {
        return ResponseEntity.ok(cityService.add(cityDto));
    }

    @GetMapping("/cities")
    public ResponseEntity<List<CityDto>> readCities(){
        return ResponseEntity.ok(cityService.getAll());
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<CityDto> readOneCity(@PathVariable final Long id) {
        return ResponseEntity.ok(cityService.getById(id));
    }

    @PutMapping("/cities/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CityDto> updateCity(@PathVariable final Long id, @RequestBody final CityDto cityDto) {
        return ResponseEntity.ok(cityService.update(id, cityDto));
    }


    @DeleteMapping("/cities/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteCity(@PathVariable final Long id) {
        cityService.delete(id);
        return ResponseEntity.ok().build();
    }
}
