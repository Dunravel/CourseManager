package pl.sda.project.coursemanager.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Block {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
