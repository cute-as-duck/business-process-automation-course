package learning.center.service;

import learning.center.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAllCourses();

    Course findByName(String courseName);

    void registerStudent(String courseName, String studentLogin);

    void removeStudent(String courseName, String studentLogin);
}
