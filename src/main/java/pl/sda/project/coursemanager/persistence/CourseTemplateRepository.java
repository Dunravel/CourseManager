package pl.sda.project.coursemanager.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTemplateRepository extends CrudRepository<CourseTemplate, Long> {
    List<CourseTemplate> findCourseTemplatesByBlocksContains(Block block);
    List<CourseTemplate> findCourseTemplatesByActiveEquals(boolean active);
}
