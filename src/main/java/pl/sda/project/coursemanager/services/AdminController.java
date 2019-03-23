package pl.sda.project.coursemanager.services;

import org.springframework.web.bind.annotation.GetMapping;
import pl.sda.project.coursemanager.persistance.CourseTemplate;

public class AdminController {

    @GetMapping("/admin/createCourse")
    private void createCourseTemplate(String courseName){
        CourseTemplate courseTemplate = new CourseTemplate(courseName);
    }
}
