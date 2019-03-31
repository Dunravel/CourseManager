package pl.sda.project.coursemanager.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
