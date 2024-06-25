package learning.center.service;

import learning.center.entity.Course;
import learning.center.entity.Student;
import learning.center.entity.UserRole;
import learning.center.exception.StudentNotFoundException;
import learning.center.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Student register(Student student) {
        student.setRole(UserRole.USER);
        student.setPassword(passwordEncoder.encode(student.password()));
        return studentRepository.save(student);
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Set<Course> findCoursesByUsername(String username) {
        return findByUsername(username).courses();
    }
}
