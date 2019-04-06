package pl.sda.project.coursemanager.persistence;

import lombok.Getter;
import lombok.Setter;
import pl.sda.project.coursemanager.domain.CourseStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String shortName;
    @OneToOne(cascade = CascadeType.PERSIST)
    private CourseTemplate courseTemplate;
    private CourseStatus status;
    private LocalDate startDate;
//    @ManyToMany(cascade = CascadeType.PERSIST)
//    private List<User> students;


    public Course() {
    }
}
