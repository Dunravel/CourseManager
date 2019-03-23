package pl.sda.project.coursemanager.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class CourseTemplate {
    @Id
    @GeneratedValue
    private Long id;
    private String courseName;
    private boolean active;

    public CourseTemplate(){

    }

    public CourseTemplate(String courseName) {
        this.courseName = courseName;
        this.active = false;
    }

    @Override
    public String toString() {
        return courseName;
    }
}
