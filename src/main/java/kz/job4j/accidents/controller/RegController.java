package kz.job4j.accidents.controller;

import kz.job4j.accidents.model.User;
import kz.job4j.accidents.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RegController {
    private final UserService userService;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        if (userService.create(user).isEmpty()) {
            return "redirect:/reg?error=true";
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(@RequestParam(value = "error", required = false) String error,
                          Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Пользователь с таким именем уже существует";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "reg";
    }
}