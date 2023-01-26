package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.AbstractService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private AbstractService<User> userService;


    @GetMapping
    public String users(ModelMap model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        User user = new User();
        user.setId(id);
        user = userService.read(user);
        model.addAttribute("user", user);
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") long id) {
        User sourceUser = new User();
        sourceUser.setId(id);
        model.addAttribute("user", userService.read(sourceUser));
        System.out.println(userService.read(sourceUser));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        user.setId(id);
        System.out.println(user);
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        User user = new User();
        user.setId(id);
        userService.delete(user);
        return "redirect:/";
    }
}
