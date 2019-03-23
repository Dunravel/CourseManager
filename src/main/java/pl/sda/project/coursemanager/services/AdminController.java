package pl.sda.project.coursemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.project.coursemanager.persistence.CourseTemplate;
import pl.sda.project.coursemanager.persistence.CourseTemplateRepository;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private CourseTemplateRepository courseTemplateRepository;

    @GetMapping("/admin")
    private ModelAndView adminDashboard(){
        ModelAndView m = new ModelAndView();
        m.setViewName("admin");
        return m;
    }

    @GetMapping("/admin/listCourses")
    private ModelAndView adminCourses(){

        Iterable<CourseTemplate> courseTemplates = courseTemplateRepository.findAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("courseList");
        view.addObject("courses",courseTemplates);
        return view;
    }

    @GetMapping("/admin/createCourse")
    private void createCourseTemplate(String courseName){
        CourseTemplate courseTemplate = new CourseTemplate(courseName);
    }
}
