package pl.sda.project.coursemanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.project.coursemanager.SignForm;
import pl.sda.project.coursemanager.persistence.Users;
import pl.sda.project.coursemanager.persistence.UsersRepository;


@Controller
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping(path = "/registration")
    public String registerUser (Model model){
        model.addAttribute("signform", new SignForm());
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String addUser(@ModelAttribute ("signform") SignForm signForm, BindingResult result) {
        System.out.println(signForm.toString());
        if (!result.hasErrors()) {
            Users users = new Users();
            users.setLogin(signForm.getLogin());
            users.setPassword(signForm.getPassword());
            users.setName(signForm.getName());
            users.setLastName(signForm.getLastName());
            users.setType("USER");

            if (usersRepository.findByLogin(signForm.getLogin())==null){
                usersRepository.save(users);
            }
            else {
                result.rejectValue("login","user.error", "User already exist");
                return "registration";
            }
            return "redirect:/login";
        }
        return "redirect:/login";
    }

    @GetMapping(path = "/login")
    public String redirectUser (){
        return "login";
    }








//    public String loginUser (){
//
//    }

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
