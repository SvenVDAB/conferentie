package be.vdab.conferentie.services;

import be.vdab.conferentie.repositories.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public int findAantalBeschikbareTickets() {
        return ticketRepository.findAantalBeschikbareTickets();
    }
}
