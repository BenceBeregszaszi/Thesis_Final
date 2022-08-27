package ekke.spring.service.exception;

public class CityAlreadyExistsException extends RuntimeException {

    public CityAlreadyExistsException(String message){
        super(message);
    }
}
