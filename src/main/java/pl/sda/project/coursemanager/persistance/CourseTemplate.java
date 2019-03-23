package pl.sda.project.coursemanager.persistance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class CourseTemplate {
    private String courseName;

    public CourseTemplate(){

    }

    public CourseTemplate(String courseName) {
        this.courseName = courseName;
    }
}
