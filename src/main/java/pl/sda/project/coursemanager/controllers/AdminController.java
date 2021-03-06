package pl.sda.project.coursemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.project.coursemanager.domain.CourseStatus;
import pl.sda.project.coursemanager.dto.BlockLesson;
import pl.sda.project.coursemanager.dto.CourseTempBlock;
import pl.sda.project.coursemanager.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private CourseTemplateRepository courseTemplateRepository;
    @Autowired
    private BlockRepository blockRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/admin")
    private ModelAndView adminDashboard() {
        ModelAndView m = new ModelAndView();
        m.setViewName("admin");
        return m;
    }

    @GetMapping("/admin/listCourseTemplates")
    private ModelAndView adminCourseTemplates() {

        Iterable<CourseTemplate> courseTemplates = courseTemplateRepository.findAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("list-course-templates");
        view.addObject("courses", courseTemplates);
        return view;
    }
//
//    @PostMapping("/admin/createCourse")
//    private void createCourseTemplate(){
//
//    }

    @GetMapping("/admin/newCourseTemplate")
    public String showAddForm(CourseTemplate courseTemplate) {
        System.out.println("test");
        return "add-course-template";
    }

    @PostMapping("/admin/addCourseTemplate")
    public String addCourseTemplate(CourseTemplate courseTemplate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-course-template";
        }

        courseTemplateRepository.save(courseTemplate);
        model.addAttribute("courses", courseTemplateRepository.findAll());
        return "list-course-templates";
    }

    @GetMapping("/admin/editCourseTemplate/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        CourseTemplate courseTemplate = courseTemplateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        model.addAttribute("courseTemplate", courseTemplate);


//        Iterable<Block> blocks = blockRepository.findAll();
        List<Long> blockIds = courseTemplate.getBlocks().stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        Iterable<Block> blocks;
        if(blockIds.size() != 0) {
            blocks = blockRepository.findBlocksByIdIsNotIn(blockIds);
        } else {
            blocks = blockRepository.findAll();
        }
        model.addAttribute("blocks",blocks);
        CourseTempBlock courseTempBlock = new CourseTempBlock();
        courseTempBlock.courseTemplateId = courseTemplate.getId();
        model.addAttribute("courseBlock", courseTempBlock);


        System.out.println(courseTempBlock.courseTemplateId);
        System.out.println(courseTempBlock.blockId);
        System.out.println(blockIds);
        System.out.println(((List<Block>) blocks).size());

        System.out.println(blockRepository.findBlocksByIdIsNotIn(Arrays.asList(1l)));
        return "edit-course-template";
    }

    @PostMapping("/admin/updateCourseTemplate/{id}")
    public String updateCourse(@PathVariable("id") Long id, CourseTemplate courseTemplate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            courseTemplate.setId(id);
            return "update-course-template";
        }

        courseTemplateRepository.save(courseTemplate);
        model.addAttribute("courses", courseTemplateRepository.findAll());
        return "list-course-templates";
    }

    @PostMapping("/admin/addBlockToCourseTemplate")
    public String addBlockToCourseTemplate(CourseTempBlock courseTempBlock, Model model){

        System.out.println(courseTempBlock.courseTemplateId);
        System.out.println(courseTempBlock.blockId);

        CourseTemplate courseTemplate = courseTemplateRepository.findById(courseTempBlock.courseTemplateId).get();
        Block block = blockRepository.findById(courseTempBlock.blockId).get();

        courseTemplate.getBlocks().add(block);
        courseTemplateRepository.save(courseTemplate);

        model.addAttribute("courseTemplate", courseTemplate);
//        Iterable<Block> blocks = blockRepository.findAll();
        List<Long> blockIds = courseTemplate.getBlocks().stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
        Iterable<Block> blocks;
        if(blockIds.size() != 0) {
            blocks = blockRepository.findBlocksByIdIsNotIn(blockIds);
        } else {
            blocks = blockRepository.findAll();
        }
        model.addAttribute("blocks",blocks);
        courseTempBlock = new CourseTempBlock();
        courseTempBlock.courseTemplateId = courseTemplate.getId();
        model.addAttribute("courseBlock", courseTempBlock);

        return "edit-course-template";
    }


    @GetMapping("/admin/listBlocks")
    private ModelAndView adminBlocks() {

        Iterable<Block> blocks = blockRepository.findAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("list-blocks");
        view.addObject("blocks", blocks);
        return view;
    }

    @GetMapping("/admin/newBlock")
    public String showBlockAddForm(Block block) {
        return "add-block";
    }

    @PostMapping("/admin/addBlock")
    public String addBlock(Block block, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-block";
        }

        blockRepository.save(block);
        model.addAttribute("blocks", blockRepository.findAll());
        return "list-blocks";
    }

    @GetMapping("/admin/editBlock/{id}")
    public String showBlockUpdateForm(@PathVariable("id") Long id, Model model) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        model.addAttribute("block", block);
        return "edit-block";
    }

    @PostMapping("/admin/updateBlock/{id}")
    public String updateBlock(@PathVariable("id") Long id, Block block, BindingResult result, Model model) {
        if (result.hasErrors()) {
            block.setId(id);
            return "edit-block";
        }

        blockRepository.save(block);
        model.addAttribute("blocks", blockRepository.findAll());
        return "list-blocks";
    }

    @GetMapping("/admin/editBlock/{id}/addLesson")
    public String showBlockLessonAddForm(@PathVariable("id") Long id, Model model) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));

        model.addAttribute("block", block);
        model.addAttribute("lessons",lessonRepository.findAll());
        BlockLesson blockLesson = new BlockLesson();
        blockLesson.setBlockId(block.getId());
        model.addAttribute("blockLesson",blockLesson);
        return "edit-block-add-lesson";
    }

    @PostMapping("/admin/addLessonToBlock")
    public String addLessonToBlock(BlockLesson blockLesson, Model model){

        Block block= blockRepository.findById(blockLesson.getBlockId()).get();
        Lesson lesson= lessonRepository.findById(blockLesson.getLessonId()).get();

        block.getLessons().add(lesson);
        lessonRepository.save(lesson);

        model.addAttribute("block", block);

        return "edit-block";
    }



    @GetMapping("/admin/listLessons")
    private ModelAndView adminLessons() {

        Iterable<Lesson> lessons = lessonRepository.findAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("list-lessons");
        view.addObject("lessons", lessons);
        return view;
    }

    @GetMapping("/admin/newLesson")
    public String showLessonAddForm(Lesson lesson) {
        return "add-lesson";
    }

    @PostMapping("/admin/addLesson")
    public String addLesson(Lesson lesson, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-lesson";
        }
        lessonRepository.save(lesson);
        model.addAttribute("lessons", lessonRepository.findAll());
        return "list-lessons";
    }

    @GetMapping("/admin/editLesson/{id}")
    public String showLessonUpdateForm(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson ID: " + id));

        model.addAttribute("lesson", lesson);
        return "edit-lesson";
    }

    @PostMapping("/admin/updateLesson/{id}")
    public String updateLesson(@PathVariable("id") Long id, Lesson lesson, BindingResult result, Model model) {
        if (result.hasErrors()) {
            lesson.setId(id);
            return "edit-lesson";
        }

        lessonRepository.save(lesson);
        model.addAttribute("lessons", lessonRepository.findAll());
        return "list-lessons";
    }

    @GetMapping("/admin/removeLesson/{id}")
    public String showLessonRemoveForm(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lesson ID: " + id));

        List<Block> blocks = blockRepository.findBlocksByLessonsContains(lesson);

        model.addAttribute("usedInBlocks",blocks);
        model.addAttribute("lesson", lesson);
        return "remove-lesson";

//        model.addAttribute("lessons",lessonRepository.findAll());
//        model.addAttribute("message","Lesson id:" + lesson.getId() + " name: " + lesson.getName() + " cannot be removed. \n This lesson is used in: " + blocks.size() +  " blocks. Remove lesson from block before trying to delete it.");
//        return "list-lessons";
    }

    @PostMapping("/admin/deleteLesson/{id}")
    public String deleteLesson(@PathVariable("id") Long id, Lesson lesson, BindingResult result, Model model) {
        if (result.hasErrors()) {
            lesson.setId(id);
            return "remove-lesson";
        }

        lessonRepository.delete(lesson);
        model.addAttribute("lessons", lessonRepository.findAll());
        return "list-lessons";
    }


    @GetMapping("/admin/removeBlock/{id}")
    public String showBlockRemoveForm(@PathVariable("id") Long id, Model model) {
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid block ID: " + id));

        List<CourseTemplate> courseTemplates = courseTemplateRepository.findCourseTemplatesByBlocksContains(block);

        if(courseTemplates.size() == 0) {
            model.addAttribute("usedInCourseTemplates", courseTemplates);
            model.addAttribute("block", block);
            return "remove-block";
        }
        model.addAttribute("blocks",blockRepository.findAll());
        model.addAttribute("message","Block id:" + block.getId() + " name: \"" + block.getName() + "\".  \nThis block cannot be removed. \n It is used in: " + courseTemplates.size() +  " course templates. \nRemove block from course template before trying to delete it.");
        return "list-blocks";
    }

    @PostMapping("/admin/deleteBlock/{id}")
    public String deleteBlock(@PathVariable("id") Long id, Block block, BindingResult result, Model model) {
        if (result.hasErrors()) {
            block.setId(id);
            return "remove-block";
        }

        blockRepository.delete(block);
        model.addAttribute("blocks", blockRepository.findAll());
        return "list-blocks";
    }

    @GetMapping("/admin/removeCourseTemplate/{id}")
    public String showCourseTemplateRemoveForm(@PathVariable("id") Long id, Model model) {
        CourseTemplate courseTemplate = courseTemplateRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid block ID: " + id));

        List<Course> courses = courseRepository.findCoursesByCourseTemplate(courseTemplate);

        if(courses.size() == 0) {
//            model.addAttribute("usedInCourses", courses);
            model.addAttribute("courseTemplate", courseTemplate);
            return "remove-course-template";
        }
        model.addAttribute("courses",courseTemplateRepository.findAll());
        model.addAttribute("message","Block id:" + courseTemplate.getId() + " name: \"" + courseTemplate.getCourseName() + "\".  \nThis template cannot be removed. \n It is used in: " + courses.size() +  " courses.");
        return "list-course-templates";
    }

    @PostMapping("/admin/deleteCourseTemplate/{id}")
    public String deleteCourseTemplate(@PathVariable("id") Long id, CourseTemplate courseTemplate, BindingResult result, Model model) {
        if (result.hasErrors()) {
            courseTemplate.setId(id);
            return "remove-course-template";
        }

        courseTemplateRepository.delete(courseTemplate);
        model.addAttribute("courses", courseTemplateRepository.findAll());
        return "list-course-templates";
    }


    @GetMapping("/admin/listCourses")
    private ModelAndView adminCourses() {

        Iterable<Course> courses = courseRepository.findAll();

        ModelAndView view = new ModelAndView();
        view.setViewName("list-courses");
        view.addObject("courses", courses);
        return view;
    }

    @GetMapping("/admin/newCourse")
    public String showCourseAddForm(Course course, Model model) {
        model.addAttribute("courseTemplates",courseTemplateRepository.findCourseTemplatesByActiveEquals(true));
        return "add-course";
    }

    @PostMapping("/admin/addCourse")
    public String addCourse(Course course, BindingResult result, Model model) {

//        if (result.hasErrors()) {
//            return "list-courses";
//        }
        courseRepository.save(course);
        model.addAttribute("courses", courseRepository.findAll());
        return "list-courses";
    }


    @GetMapping("/admin/editCourse/{id}")
    public String showCourseUpdateForm(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));

        model.addAttribute("course", course);
        model.addAttribute("courseTemplates",courseTemplateRepository.findCourseTemplatesByActiveEquals(true));
        return "add-course";
    }

    @GetMapping("/admin/removeCourse/{id}")
    public String showCourseRemoveForm(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + id));

        if(course.getStatus() == CourseStatus.DRAFT || course.getStatus() == null) {
//            model.addAttribute("usedInCourses", courses);
            model.addAttribute("course", course);
            return "remove-course";
        }
        model.addAttribute("courses",courseRepository.findAll());
        model.addAttribute("message","Block id:" + course.getId() + " name: \"" + course.getName() + "\".  \nThis course cannot be removed. \n It is not in DRAFT status.");
        return "list-courses";
    }

    @PostMapping("/admin/deleteCourse/{id}")
    public String deleteCourse(@PathVariable("id") Long id, Course course, BindingResult result, Model model) {
        if (result.hasErrors()) {
            course.setId(id);
            return "remove-course";
        }

        courseRepository.delete(course);
        model.addAttribute("courses", courseRepository.findAll());
        return "list-courses";
    }

}
