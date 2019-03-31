package pl.sda.project.coursemanager.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Setter
@Getter
public class CourseTemplate {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Course name is mandatory")
    private String courseName;
    private boolean active;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH})
    private List<Block> blocks;

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
