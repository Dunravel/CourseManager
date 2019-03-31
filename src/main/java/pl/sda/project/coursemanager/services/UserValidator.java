package pl.sda.project.coursemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.sda.project.coursemanager.persistence.User;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "This field is required.");

        if (userService.findByLogin(user.getUsername()) != null){
            errors.rejectValue("username", "Someone already has that login.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "This field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","This field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","This field is required.");


    }
}
