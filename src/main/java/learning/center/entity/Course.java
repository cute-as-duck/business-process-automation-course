package learning.center.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import learning.center.exception.RegistrationIsClosedException;
import learning.center.exception.StudentAlreadyEnrolledException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String courseName;
    private int maxStudents;
    private int currentStudents;
    private double courseFee;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="course_student",
            joinColumns=  @JoinColumn(name="course_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="student_id", referencedColumnName="id") )
    private Set<Student> students = new HashSet<>();

    public long id() {
        return id;
    }

    public String courseName() {
        return courseName;
    }

    public int maxStudents() {
        return maxStudents;
    }

    public int currentStudents() {
        return currentStudents;
    }

    public double courseFee() {
        return courseFee;
    }

    public Set<Student> students() {
        return students;
    }

    public void addStudent(Student student) {
        if (this.students.contains(student)) {
            throw new StudentAlreadyEnrolledException();
        } else if (this.currentStudents >= this.maxStudents) {
            throw new RegistrationIsClosedException();
        }
        this.students.add(student);
        this.currentStudents++;
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        this.currentStudents--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        if (maxStudents != course.maxStudents) return false;
        if (currentStudents != course.currentStudents) return false;
        return courseName != null ? courseName.equals(course.courseName) : course.courseName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + maxStudents;
        result = 31 * result + currentStudents;
        return result;
    }
}
