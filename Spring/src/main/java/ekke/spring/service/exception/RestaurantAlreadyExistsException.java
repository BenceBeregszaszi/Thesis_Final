package ekke.spring.service.exception;

public class RestaurantAlreadyExistsException extends RuntimeException {

    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
