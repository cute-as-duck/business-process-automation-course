package learning.center.service;

import learning.center.entity.Course;
import learning.center.entity.Student;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface StudentService {

    Student register(Student student);

    Student findByUsername(String username);

    Set<Course> findCoursesByUsername(String username);
}
