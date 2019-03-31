package pl.sda.project.coursemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.project.coursemanager.persistence.User;
import pl.sda.project.coursemanager.services.UserService;
import pl.sda.project.coursemanager.services.UserValidator;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping(path = "/registration")
    public String registerUser(Model model) {
        model.addAttribute("usrform", new User());
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String addUser(@ModelAttribute("usrform") User usrform, BindingResult result) {
        userValidator.validate(usrform, result);

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(usrform);

        return "redirect:/welcome";
    }

    @GetMapping(path = "/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}