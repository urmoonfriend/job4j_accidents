package kz.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getDefault(Model model) {
        model.addAttribute("user", "Petr Arsentev");
        return "index";
    }
}
