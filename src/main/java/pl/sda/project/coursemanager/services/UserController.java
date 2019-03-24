package pl.sda.project.coursemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.project.coursemanager.persistence.Users;
import pl.sda.project.coursemanager.persistence.UsersRepository;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/signup")
    public String registerUser (Users users){
        return "signform";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid Users users, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }

        usersRepository.save(users);
        model.addAttribute("users", usersRepository.findAll());
        return "registration";
    }

//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        Users user = usersRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//
//        model.addAttribute("user", user);
//        return "update-user";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") long id, @Valid Users user,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
//
//        usersRepository.save(user);
//        model.addAttribute("users", usersRepository.findAll());
//        return "index";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") long id, Model model) {
//        Users user = usersRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        usersRepository.delete(user);
//        model.addAttribute("users", usersRepository.findAll());
//        return "index";
//    }
}
