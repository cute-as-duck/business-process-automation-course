package learning.center.exception;

public class RegistrationIsClosedException extends RuntimeException{

    public static final String message = "Registration for the course is closed";

    public RegistrationIsClosedException() {
        super(message);
    }
}
