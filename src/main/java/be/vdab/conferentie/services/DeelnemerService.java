package be.vdab.conferentie.services;

import be.vdab.conferentie.domain.Deelnemer;
import be.vdab.conferentie.exceptions.DeelnemerIsAlIngeschrevenException;
import be.vdab.conferentie.repositories.DeelnemerRepository;
import be.vdab.conferentie.repositories.DeelnemerVoorkeurSessieRepository;
import be.vdab.conferentie.repositories.TicketRepository;
import be.vdab.conferentie.sessions.Voorkeursessies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DeelnemerService {
    private final DeelnemerRepository deelnemerRepository;
    private final TicketRepository ticketRepository;
    private final DeelnemerVoorkeurSessieRepository deelnemerVoorkeurSessieRepository;
    private final Voorkeursessies voorkeursessies;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public DeelnemerService(DeelnemerRepository deelnemerRepository, TicketRepository ticketRepository,
                            DeelnemerVoorkeurSessieRepository deelnemerVoorkeurSessieRepository,
                            Voorkeursessies voorkeursessies) {
        this.deelnemerRepository = deelnemerRepository;
        this.ticketRepository = ticketRepository;
        this.deelnemerVoorkeurSessieRepository = deelnemerVoorkeurSessieRepository;
        this.voorkeursessies = voorkeursessies;
    }

    @Transactional
    public void create(Deelnemer deelnemer) {
        if (deelnemerRepository.exists(deelnemer.getEmail())) {
            logger.error("De deelnemer was reeds ingeschreven.");
            throw new DeelnemerIsAlIngeschrevenException();
        }
        var deelnemerId = deelnemerRepository.create(deelnemer);
        ticketRepository.lowerNrOfTicketsByOne();
        deelnemerVoorkeurSessieRepository.create(deelnemerId, voorkeursessies.getSessieIds());
        voorkeursessies.clear();
    }
}
