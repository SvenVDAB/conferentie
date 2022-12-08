package be.vdab.conferentie.controllers;

import be.vdab.conferentie.domain.Deelnemer;
import be.vdab.conferentie.services.DagService;
import be.vdab.conferentie.services.DeelnemerService;
import be.vdab.conferentie.services.TicketService;
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
    private final TicketService ticketService;
    private final DagService dagService;

    public DeelnemerController(DeelnemerService deelnemerService, TicketService ticketService, DagService dagService) {
        this.deelnemerService = deelnemerService;
        this.ticketService = ticketService;
        this.dagService = dagService;
    }

    @GetMapping
    public ModelAndView deelnemerForm() {
        return new ModelAndView("deelnemer")
                .addObject(new Deelnemer(0, "", "", ""));
    }

    @PostMapping
    public ModelAndView deelnemerToevoegen(@Valid Deelnemer deelnemer, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("deelnemer");
        }
        deelnemerService.create(deelnemer);
        var modelAndView = new ModelAndView("index");
        modelAndView.addObject("aantalTickets", ticketService.findAantalBeschikbareTickets());
        modelAndView.addObject("datums", dagService.findAll());
        return modelAndView;
    }
}
