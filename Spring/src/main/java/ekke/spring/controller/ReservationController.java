package ekke.spring.controller;

import ekke.spring.dto.ReservationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController extends ControllerBase{

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody final ReservationDto reservationDto){
        return null;
    }

    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDto>> readReservation(){
        return null;
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable final Long id, @RequestBody final ReservationDto reservationDto){
        return null;
    }


    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable final Long id){
        return null;
    }
}
