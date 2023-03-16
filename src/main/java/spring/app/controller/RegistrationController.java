package spring.app.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.app.entity.User;
import spring.app.service.UserService;

import javax.naming.Binding;

@Controller
public class RegistrationController {
    @Autowired
    private UserService service;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid User userForm, BindingResult result, Model model){
        if (result.hasErrors()){
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Incorrect password");
            return "registration";
        }
        if (!service.saveUser(userForm)){
            model.addAttribute("usernameError", "This username is already in used");
            return "registration";
        }
        return "redirect:/";
    }
}
