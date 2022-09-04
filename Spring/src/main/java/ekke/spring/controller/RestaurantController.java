package ekke.spring.controller;

import ekke.spring.dto.RestaurantDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController extends ControllerBase{

    @PostMapping("/restaurant")
    public ResponseEntity<RestaurantDto> createReservation(@RequestBody final RestaurantDto restaurantDto){
        return null;
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<RestaurantDto>> readRestaurant(){
        return null;
    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable final Long id, @RequestBody final RestaurantDto restaurantDto){
        return null;
    }


    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable final Long id){
        return null;
    }
}
