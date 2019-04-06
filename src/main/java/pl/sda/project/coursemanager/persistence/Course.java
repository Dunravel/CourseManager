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
    private String name;
    private String shortName;
    @OneToOne(cascade= CascadeType.PERSIST)
    private CourseTemplate courseTemplate;
//    @OneToMany(cascade= CascadeType.PERSIST)
//    private User teacher;
    private LocalDate startDate;
//    private List<Users> users;


    public Course() {
    }
}
