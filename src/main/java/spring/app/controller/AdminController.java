package spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.app.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService service;

    @GetMapping("/admin")
    public String userList(Model model){
        model.addAttribute("allUsers", service.findAllUsers());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long id,
                             @RequestParam(required = true, defaultValue = "") String action, Model model){
        if (action.equals("delete")){
            service.deleteUser(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/get/{userId}")
    public String getUser(@PathVariable("userId") Long id, Model model){
        model.addAttribute("allUsers", service.userList(id));
        return "admin";
    }
}
