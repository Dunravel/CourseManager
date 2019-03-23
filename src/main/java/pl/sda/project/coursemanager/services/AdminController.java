package pl.sda.project.coursemanager.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.project.coursemanager.persistence.CourseTemplate;

@RestController
public class AdminController {

    @GetMapping("/admin")
    private ModelAndView adminDashboard(){
        ModelAndView m = new ModelAndView();
        m.setViewName("admin");
        return m;
    }

    @GetMapping("/admin/createCourse")
    private void createCourseTemplate(String courseName){
        CourseTemplate courseTemplate = new CourseTemplate(courseName);
    }
}
