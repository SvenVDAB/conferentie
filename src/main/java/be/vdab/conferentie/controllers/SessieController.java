package be.vdab.conferentie.controllers;

import be.vdab.conferentie.services.SessieService;
import be.vdab.conferentie.sessions.Voorkeursessies;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sessie")
public class SessieController {
    private final SessieService sessieService;
    private final Voorkeursessies voorkeursessies;

    public SessieController(SessieService sessieService, Voorkeursessies voorkeursessies) {
        this.sessieService = sessieService;
        this.voorkeursessies = voorkeursessies;
    }

    @GetMapping("{sessieId}")
    public ModelAndView toonSessieGegevens(@PathVariable long sessieId) {
        var modelAndView = new ModelAndView("sessie");
        sessieService.findBySessieId(sessieId).ifPresent(sessieDetails -> {
            modelAndView.addObject("sessiedetails", sessieDetails);
            modelAndView.addObject("interessant",
                    voorkeursessies.getSessieIds().contains(sessieId)
            );
        });
        return modelAndView;
    }

    @PostMapping("{sessieId}")
    public String pasSessieGegevensAan(@PathVariable long sessieId) {
        if (voorkeursessies.getSessieIds().contains(sessieId)) {
            voorkeursessies.verwijder(sessieId);
        } else {
            voorkeursessies.voegToe(sessieId);
        }
        return "redirect:/sessie/{sessieId}";
    }

}

