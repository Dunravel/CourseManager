package pl.sda.project.coursemanager.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private CourseTemplate courseTemplate;
    private LocalDate startDate;
    private List<Users> users;

    public Course() {
    }
}
