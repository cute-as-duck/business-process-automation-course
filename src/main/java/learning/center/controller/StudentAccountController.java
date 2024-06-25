package learning.center.controller;

import learning.center.service.CourseService;
import learning.center.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class StudentAccountController {

    private final CourseService courseService;
    private final StudentService studentService;

    @GetMapping
    public String getCourses(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("courses", this.studentService.findCoursesByUsername(userDetails.getUsername()));
        return "student_account_page";
    }

    @GetMapping ("/delete/{courseName}")
    public String deleteCourse(@PathVariable String courseName,
                               @AuthenticationPrincipal UserDetails userDetails) {
        courseService.removeStudent(courseName, userDetails.getUsername());
        return "redirect:/account";
    }
}
