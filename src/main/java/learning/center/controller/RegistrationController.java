package learning.center.controller;

import learning.center.entity.Student;
import learning.center.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final StudentService studentService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("student", new Student());
        return "registration_page";
    }

    @PostMapping("/registration")
    public String registerStudent(@ModelAttribute("student") Student student) {
        System.out.println(student.firstName());
        System.out.println(student.lastName());
        studentService.register(student);
        return "redirect:/login?success";
    }
}
