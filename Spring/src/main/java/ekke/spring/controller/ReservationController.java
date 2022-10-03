package ekke.spring.controller;

import ekke.spring.dto.ReservationDto;
import ekke.spring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController extends ControllerBase {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservations")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody final ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.add(reservationDto));
    }

    @GetMapping("/reservations")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<ReservationDto>> readReservation() {
        return ResponseEntity.ok(reservationService.getAll());
    }

    @GetMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ReservationDto> readOneReservation(@PathVariable final Long id) {
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @PutMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable final Long id, @RequestBody final ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.update(id, reservationDto));
    }


    @DeleteMapping("/reservations/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Void> deleteReservation(@PathVariable final Long id) {
        reservationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
