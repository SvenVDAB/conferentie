package be.vdab.conferentie.controllers;

import be.vdab.conferentie.services.DagService;
import be.vdab.conferentie.services.SessieService;
import be.vdab.conferentie.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class SessieOverzichtController {
    private final TicketService ticketService;
    private final SessieService sessieService;
    private final DagService dagService;

    public SessieOverzichtController(TicketService ticketService, SessieService sessieService, DagService dagService) {
        this.ticketService = ticketService;
        this.sessieService = sessieService;
        this.dagService = dagService;
    }

    @GetMapping
    public ModelAndView toonAantalTickets() {
        var modelAndView = new ModelAndView("index");
        modelAndView.addObject("aantalTickets", ticketService.findAantalBeschikbareTickets());
        modelAndView.addObject("datums", dagService.findAll());
        return modelAndView;
    }

    @GetMapping("{dagId}/sessieoverzicht")
    public ModelAndView toonAantalTicketsEnSessieoverzicht(@PathVariable long dagId) {
        var modelAndView = new ModelAndView("index");
        var datums = dagService.findAll();
        modelAndView.addObject("aantalTickets", ticketService.findAantalBeschikbareTickets());
        modelAndView.addObject("datums", datums);
        modelAndView.addObject("gekozenDatum", datums.stream()
                .filter(dag -> dag.getId() == dagId).findFirst().get().getDatum());
        modelAndView.addObject("sessies", sessieService.findByDayId(dagId));
        return modelAndView;
    }
}
