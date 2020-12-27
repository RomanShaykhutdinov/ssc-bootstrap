package servingweb.controller;

import org.springframework.web.bind.annotation.*;
import servingweb.model.User;
import servingweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);

        return "redirect:/main";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUser(user);

        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String showUser(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "main";
    }

    @GetMapping("/edit")
    @ResponseBody
    public User findOne(long id) {
        return userService.getUserById(id);
    }
}

