package be.vdab.conferentie.controllers;

import be.vdab.conferentie.domain.Deelnemer;
import be.vdab.conferentie.services.DeelnemerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/deelnameformulier")
public class DeelnemerController {
    private final DeelnemerService deelnemerService;

    public DeelnemerController(DeelnemerService deelnemerService) {
        this.deelnemerService = deelnemerService;
    }

    @GetMapping
    public ModelAndView deelnemerForm() {
        return new ModelAndView("deelnemer")
                .addObject(new Deelnemer(0, "", "", ""));
    }

    @PostMapping
    public String deelnemerToevoegen(@Valid Deelnemer deelnemer, Errors errors) {
        if (errors.hasErrors()) {
            return "deelnemer";
        }
        deelnemerService.create(deelnemer);
        return "redirect:/";
    }
}
