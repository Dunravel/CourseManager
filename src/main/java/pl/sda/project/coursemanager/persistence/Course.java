package pl.sda.project.coursemanager.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(cascade= CascadeType.PERSIST)
    private CourseTemplate courseTemplate;
    private String name;
    private String shortName;
    private LocalDate startDate;
    private User teacher;
//    private List<Users> users;


    public Course() {
    }
}
