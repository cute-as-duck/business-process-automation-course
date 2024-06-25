package learning.center.exception;

public class StudentNotFoundException extends RuntimeException{

    private static final String message = "Student not found";

    public StudentNotFoundException() {
        super(message);
    }
}
