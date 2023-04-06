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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<ReservationDto>> readReservation() {
        return ResponseEntity.ok(reservationService.getAll());
    }

    @GetMapping("/reservations/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ReservationDto> readOneReservation(@PathVariable final Long id) {
        return ResponseEntity.ok(reservationService.getById(id));
    }

    @GetMapping("/{userId}/reservations")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<ReservationDto>> readUserReservation(@PathVariable Long userId) {
        return ResponseEntity.ok(reservationService.getByUserId(userId));
    }

    @PutMapping("/reservations/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable final Long id, @RequestBody final ReservationDto reservationDto) {
        return ResponseEntity.ok(reservationService.update(id, reservationDto));
    }


    @DeleteMapping("/reservations/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<Void> deleteReservation(@PathVariable final Long id) {
        reservationService.delete(id);
        return ResponseEntity.ok().build();
    }
}
