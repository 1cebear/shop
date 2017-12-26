package ru.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.shop.controller.user.AbstractUserController;
import ru.shop.model.User;

import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController {
    @GetMapping(value = "/")
    public String root(Model model) {
        return "redirect:index";
    }

    @GetMapping(value = "/index")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid User user, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "redirect:index";
        } else {
            super.create(user);
            status.setComplete();
            return "redirect:index";
        }
    }
}
