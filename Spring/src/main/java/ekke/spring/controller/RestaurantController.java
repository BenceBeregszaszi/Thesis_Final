package ekke.spring.controller;

import ekke.spring.dto.RestaurantDto;
import ekke.spring.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController extends ControllerBase {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurants")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody final RestaurantDto restaurantDto) {
        return ResponseEntity.ok(restaurantService.add(restaurantDto));
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDto>> readRestaurant() {
        return ResponseEntity.ok(restaurantService.getAll());
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDto> readOneRestaurant(@PathVariable final Long id) {
        return ResponseEntity.ok(restaurantService.getById(id));
    }

    @PutMapping("/restaurants/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable final Long id, @RequestBody final RestaurantDto restaurantDto) {
        return ResponseEntity.ok(restaurantService.update(id, restaurantDto));
    }


    @DeleteMapping("/restaurants/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable final Long id) {
        restaurantService.delete(id);
        return ResponseEntity.ok().build();
    }
}
