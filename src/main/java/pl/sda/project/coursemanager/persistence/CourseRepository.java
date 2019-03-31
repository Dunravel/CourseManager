package pl.sda.project.coursemanager.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findCoursesByCourseTemplate(CourseTemplate courseTemplate);

}
