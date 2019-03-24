package pl.sda.project.coursemanager.persistence;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Long> {
    Users findByLogin(String login);
}
