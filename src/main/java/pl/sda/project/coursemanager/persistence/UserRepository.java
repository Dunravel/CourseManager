package pl.sda.project.coursemanager.persistence;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByLogin(String username);
}
