package pl.sda.project.coursemanager.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
