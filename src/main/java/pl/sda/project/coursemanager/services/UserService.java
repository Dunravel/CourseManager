package pl.sda.project.coursemanager.services;

import org.springframework.stereotype.Service;
import pl.sda.project.coursemanager.persistence.User;

@Service
public interface UserService {
    void save(User user);

    User findByLogin(String login);
}
