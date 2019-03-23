package pl.sda.project.coursemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.project.coursemanager.SignForm;
import pl.sda.project.coursemanager.persistence.UsersRepository;

public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping (value = "signup")
    public String addUser (Model model){
        model.addAttribute("signform", new SignForm());
        return "signform";
    }

}
