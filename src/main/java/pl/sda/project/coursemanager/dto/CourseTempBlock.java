package pl.sda.project.coursemanager.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CourseTempBlock {
    public Long courseTemplateId;
    public Long blockId;
}
