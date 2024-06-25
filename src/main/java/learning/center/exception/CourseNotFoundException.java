package learning.center.exception;

public class CourseNotFoundException extends RuntimeException{

    private static final String message = "Course not found";

    public CourseNotFoundException() {
        super(message);
    }
}
