package kz.job4j.accidents.controller;

import kz.job4j.accidents.model.User;
import kz.job4j.accidents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegController {
    private final UserService userService;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("errorMessage", "Пользователь с таким именем уже существует");
        } else {
            userService.create(user);
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "users/reg";
    }
}