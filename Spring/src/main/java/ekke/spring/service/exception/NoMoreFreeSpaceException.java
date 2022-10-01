package ekke.spring.service.exception;

public class NoMoreFreeSpaceException extends RuntimeException {

    public NoMoreFreeSpaceException(final String message) {
        super(message);
    }
}
