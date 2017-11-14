package ru.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping(value = "/")
    public String root() {
        return "redirect:index";
    }

    @GetMapping(value = "/index")
    public String login() {
        return "index";
    }
}
