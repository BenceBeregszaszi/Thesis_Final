package ekke.spring.controller;

import ekke.spring.common.exception.ValidationException;
import ekke.spring.service.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
public class ControllerBase {

    @ExceptionHandler({
            CityAlreadyExistsException.class,
            ReservationAlreadyExists.class,
            RestaurantAlreadyExistsException.class,
            UserAlreadyExistsException.class
    })
    public ResponseEntity<String> handleAlreadyException(final Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler({CityNotFoundException.class,
            ReservationNotFoundException.class,
            RestaurantNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<String> handleNotFoundException(final Exception exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<String> handleValidationException(final ValidationException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(final AuthenticationException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }

    @ExceptionHandler({NoMoreFreeSpaceException.class})
    public ResponseEntity<String> handleNoMoreFreeSpaceException(final NoMoreFreeSpaceException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
    }
}
