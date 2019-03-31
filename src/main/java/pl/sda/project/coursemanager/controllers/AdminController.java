package pl.sda.project.coursemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.project.coursemanager.persistence.CourseTemplate;
import pl.sda.project.coursemanager.persistence.CourseTemplateRepository;

@Controller
public class AdminController {

    @Autowired
    private CourseTemplateRepository courseTemplateRepository;

    @GetMapping("/admin")
    private ModelAndView adminDashboard(){
        ModelAndView m = new ModelAndView();
        m.setViewName("admin");
        return m;
    }

    @GetMapping("/admin/listCourseTemplates")
    private ModelAndView adminCourses(){

        Iterable<CourseTemplate> courseTemplates = courseTemplateRepository.findAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("list-course-templates");
        view.addObject("courses",courseTemplates);
        return view;
    }
//
//    @PostMapping("/admin/createCourse")
//    private void createCourseTemplate(){
//
//    }

    @GetMapping("/admin/newCourseTemplate")
    public String showAddForm(CourseTemplate courseTemplate){
        System.out.println("test");
        return "add-course-template";
    }

    @PostMapping("/admin/addCourseTemplate")
    public String addCourseTemplate(CourseTemplate courseTemplate, BindingResult result, Model model){
        if(result.hasErrors()){
            return "new-course-template";
        }

        courseTemplateRepository.save(courseTemplate);
        model.addAttribute("courses",courseTemplateRepository.findAll());
        return "list-course-templates";
    }

    @GetMapping("/admin/editCourseTemplate/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        CourseTemplate courseTemplate = courseTemplateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        model.addAttribute("courseTemplate",courseTemplate);
        return "edit-course-template";
    }

    @PostMapping("/admin/updateCourseTemplate/{id}")
    public String updateCourse(@PathVariable("id") Long id, CourseTemplate courseTemplate, BindingResult result, Model model){
        if(result.hasErrors()){
            courseTemplate.setId(id);
            return "update-course-template";
        }

        courseTemplateRepository.save(courseTemplate);
        model.addAttribute("courses", courseTemplateRepository.findAll());
        return "list-course-templates";
    }
}
