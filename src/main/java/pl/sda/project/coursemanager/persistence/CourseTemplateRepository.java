package pl.sda.project.coursemanager.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTemplateRepository extends CrudRepository<CourseTemplate, Long> {
}
