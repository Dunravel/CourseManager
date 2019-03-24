package pl.sda.project.coursemanager.services;

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

    @GetMapping("/admin/listCourses")
    private ModelAndView adminCourses(){

        Iterable<CourseTemplate> courseTemplates = courseTemplateRepository.findAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("courseList");
        view.addObject("courses",courseTemplates);
        return view;
    }
//
//    @PostMapping("/admin/createCourse")
//    private void createCourseTemplate(){
//
//    }

    @GetMapping("/admin/newCourse")
    public String showAddForm(CourseTemplate courseTemplate){
        System.out.println("test");
        return "add-course";
    }

    @PostMapping("/admin/addCourse")
    public String addCourse(CourseTemplate courseTemplate, BindingResult result, Model model){
        if(result.hasErrors()){
            return "new-course";
        }

        courseTemplateRepository.save(courseTemplate);
        model.addAttribute("courses",courseTemplateRepository.findAll());
        return "admin";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        CourseTemplate courseTemplate = courseTemplateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        model.addAttribute("course",courseTemplate);
        return "update-course";
    }

    @PostMapping("/admin/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, CourseTemplate courseTemplate, BindingResult result, Model model){
        if(result.hasErrors()){
            courseTemplate.setId(id);
            return "update-course";
        }

        courseTemplateRepository.save(courseTemplate);
        model.addAttribute("courses", courseTemplateRepository.findAll());
        return "admin";
    }
}
