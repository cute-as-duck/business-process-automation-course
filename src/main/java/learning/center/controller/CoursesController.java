package learning.center.controller;

import learning.center.dto.PaymentRequest;
import learning.center.dto.PaymentResponse;
import learning.center.entity.Course;
import learning.center.entity.Student;
import learning.center.entity.TransactionStatus;
import learning.center.service.CourseService;
import learning.center.service.PaymentService;
import learning.center.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CoursesController {

    private final CourseService courseService;
    private final PaymentService paymentService;
    private final StudentService studentService;

    @GetMapping("/")
    public String getCourses(Model model,
                             @ModelAttribute(name="alreadyRegistered") String alreadyRegistered) {
        model.addAttribute("courses", this.courseService.findAllCourses());
        model.addAttribute("alreadyRegistered", alreadyRegistered.equals("true"));
        return "courses_page";
    }

    @GetMapping("/payment/{courseName}")
    public String paymentInfo(@PathVariable String courseName,
                              @AuthenticationPrincipal UserDetails userDetails,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        Course course = courseService.findByName(courseName);

        boolean isEnrolled = studentService.findCoursesByUsername(userDetails.getUsername())
                .stream().anyMatch(c -> c.courseName().equals(courseName));
        if (isEnrolled) {
            System.out.println("Is enrolled");
            redirectAttributes.addFlashAttribute("alreadyRegistered", "true");
            return "redirect:/";
        } else {
            model.addAttribute("course", course);
            model.addAttribute("paymentRequest", new PaymentRequest());
            return "payment_page";
        }
    }

    @PostMapping("/makePayment/{courseName}")
    public String makePayment(@PathVariable String courseName,
                              @ModelAttribute PaymentRequest paymentRequest,
                              @AuthenticationPrincipal UserDetails userDetails) {
        Student student = studentService.findByUsername(userDetails.getUsername());
        Course course = courseService.findByName(courseName);

        paymentRequest.setStudentId(student.id());
        paymentRequest.setCourseFee(course.courseFee());
        PaymentResponse paymentResponse = paymentService.makePayment(paymentRequest);
        if (paymentResponse.getTransactionStatus().equals(TransactionStatus.SUCCESS)) {
            courseService.registerStudent(courseName, userDetails.getUsername());
        }
        return "redirect:/account";
    }
}
