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
    private LocalDate startDate;
//    private List<Users> users;


    public Course() {
    }
}
