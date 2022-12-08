package be.vdab.conferentie.controllers;

import be.vdab.conferentie.sessions.Voorkeursessies;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MyControllerAdvice {
    private final Voorkeursessies voorkeursessies;

    public MyControllerAdvice(Voorkeursessies voorkeursessies) {
        this.voorkeursessies = voorkeursessies;
    }
    @ModelAttribute
    void extraDataToevoegenAanModel(Model model) {
        model.addAttribute(voorkeursessies);
    }
}
