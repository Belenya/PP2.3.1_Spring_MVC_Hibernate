package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.services.AbstractService;

import javax.validation.Valid;
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
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String updateUser(Model model, @PathVariable("id") long id) {
        User sourceUser = new User();
        sourceUser.setId(id);
        model.addAttribute("user", userService.read(sourceUser));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        user.setId(id);
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
