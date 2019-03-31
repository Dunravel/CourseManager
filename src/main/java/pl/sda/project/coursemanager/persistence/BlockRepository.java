package pl.sda.project.coursemanager.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlockRepository extends CrudRepository<Block,Long> {
    public List<Block> findBlocksByIdIsNotIn(List<Long> ids);
    public List<Block> findBlocksByLessonsContains(Lesson lesson);

}


