package kz.job4j.accidents.controller;

import kz.job4j.accidents.model.Accident;
import kz.job4j.accidents.service.AccidentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidents;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("accidents", accidents.getAll());
        return "/accidents/list";
    }

    @GetMapping("/create")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidents.getTypes());
        return "/accidents/create";
    }

    @GetMapping("/{accidentId}")
    public String getAccident(@PathVariable("accidentId") Integer id, Model model) {
        var accidentOptional = accidents.getById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("types", accidents.getTypes());
        model.addAttribute("accident", accidentOptional.get());
        return "/accidents/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidents.create(accident);
        return "redirect:/";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidents.update(accident);
        return "redirect:/";
    }
}