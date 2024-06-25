package learning.center.exception;

public class StudentAlreadyEnrolledException extends RuntimeException{

    public static final String message = "Student already enrolled in the course";

    public StudentAlreadyEnrolledException() {
        super(message);
    }
}
