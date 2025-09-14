package csci318.demo.cargotracker.routingms.domain.model.exceptions;

public class NonTransitPathFoundException extends RuntimeException {
    public NonTransitPathFoundException(String message) {
        super(message);
    }
}
