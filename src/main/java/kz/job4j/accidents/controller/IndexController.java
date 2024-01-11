package kz.job4j.accidents.controller;

import kz.job4j.accidents.service.AccidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final AccidentService accidentService;

    @GetMapping("/")
    public String getDefault(Model model) {
        model.addAttribute("accidents", accidentService.getAll());
        return "index";
    }
}
