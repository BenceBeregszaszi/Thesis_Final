package ekke.spring.controller;

import ekke.spring.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerBase {

    @ExceptionHandler({
            CityAlreadyExistsException.class,
            ReservationAlreadyExists.class,
            RestaurantAlreadyExistsException.class,
            UserAlreadyExistsException.class
    })
    public ResponseEntity<String> handleAlreadyException(final Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({CityNotFoundException.class,
            ReservationNotFoundException.class,
            RestaurantNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(final Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
