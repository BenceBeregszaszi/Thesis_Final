package ekke.spring.service.exception;

public class ReservationAlreadyExists extends RuntimeException {

    public ReservationAlreadyExists(String message){
        super(message);
    }
}
