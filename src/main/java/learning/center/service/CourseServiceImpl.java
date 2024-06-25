package learning.center.service;

import learning.center.entity.Course;
import learning.center.exception.CourseNotFoundException;
import learning.center.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findByName(String courseName) {
        return courseRepository.findByCourseName(courseName).orElseThrow(CourseNotFoundException::new);
    }

    @Override
    @Transactional
    public void registerStudent(String courseName, String username) {
        findByName(courseName).addStudent(studentService.findByUsername(username));
    }

    @Override
    @Transactional
    public void removeStudent(String courseName,String username) {
        findByName(courseName).removeStudent(studentService.findByUsername(username));
    }
}
