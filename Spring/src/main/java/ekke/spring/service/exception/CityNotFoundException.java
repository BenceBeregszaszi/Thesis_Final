package ekke.spring.service.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String message){
        super(message);
    }
}
