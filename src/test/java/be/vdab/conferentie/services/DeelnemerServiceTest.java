package be.vdab.conferentie.services;

import be.vdab.conferentie.domain.Deelnemer;
import be.vdab.conferentie.exceptions.DeelnemerIsAlIngeschrevenException;
import be.vdab.conferentie.exceptions.GeenTicketsBeschikbaarException;
import be.vdab.conferentie.exceptions.NoSessionsChosenException;
import be.vdab.conferentie.repositories.DeelnemerRepository;
import be.vdab.conferentie.repositories.DeelnemerVoorkeurSessieRepository;
import be.vdab.conferentie.repositories.TicketRepository;
import be.vdab.conferentie.sessions.Voorkeursessies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeelnemerServiceTest {
    private DeelnemerService service;
    @Mock
    private DeelnemerRepository deelnemerRepository;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private DeelnemerVoorkeurSessieRepository deelnemerVoorkeurSessieRepository;

    @Mock
    private Voorkeursessies voorkeursessies;
    private final Deelnemer deelnemer1 =
            new Deelnemer(0, "Sven", "Bouwen", "svenver28@hotmail.com");
    private final Deelnemer deelnemer2 =
            new Deelnemer(0, "Donald", "Trump", "fakenews@donald.trump");

    @BeforeEach
    void beforeEach() {
        service = new DeelnemerService(deelnemerRepository, ticketRepository,
                deelnemerVoorkeurSessieRepository, voorkeursessies);
    }

    @Test
    void createRoeptCorrecteMethodsOpEnWerptGeenFouten() {
        when(deelnemerRepository.create(deelnemer1)).thenReturn(1L);
        when(deelnemerRepository.exists("svenver28@hotmail.com")).thenReturn(false);
        when(ticketRepository.lowerNrOfTicketsByOne()).thenReturn(1);
        when(voorkeursessies.getSessieIds()).thenReturn(Set.of(10L, 20L));
        when(deelnemerVoorkeurSessieRepository.create(1L, voorkeursessies.getSessieIds()))
                .thenReturn(2);
        service.create(deelnemer1);
        verify(deelnemerVoorkeurSessieRepository).create(1L, voorkeursessies.getSessieIds());
        verify(ticketRepository).lowerNrOfTicketsByOne();
    }

    @Test
    void createBijReedsIngeschrevenDeelnemerRoeptDeelnemerIsAlIngeschrevenExceptionOp() {
        when(deelnemerRepository.exists("fakenews@donald.trump")).thenReturn(true);
        assertThatExceptionOfType(DeelnemerIsAlIngeschrevenException.class)
                .isThrownBy(()->service.create(deelnemer2));
    }

    @Test
    void createBijTeWeinigBeschikbareTicketsRoeptGeenTicketsBeschikbaarExceptionOp() {
        when(deelnemerRepository.create(deelnemer1)).thenReturn(1L);
        when(deelnemerRepository.exists("svenver28@hotmail.com")).thenReturn(false);
        when(ticketRepository.lowerNrOfTicketsByOne()).thenThrow(GeenTicketsBeschikbaarException.class);
        assertThatExceptionOfType(GeenTicketsBeschikbaarException.class)
                .isThrownBy(()->service.create(deelnemer1));
    }

    @Test
    void createZonderSessiesTeKiezenRoeptNoSessionsChosenExceptionOp() {
        when(deelnemerRepository.create(deelnemer1)).thenReturn(1L);
        when(deelnemerRepository.exists("svenver28@hotmail.com")).thenReturn(false);
        when(ticketRepository.lowerNrOfTicketsByOne()).thenReturn(1);
        when(voorkeursessies.getSessieIds()).thenReturn(Set.of());
        when(deelnemerVoorkeurSessieRepository.create(1L, Set.of()))
                .thenThrow(NoSessionsChosenException.class);
        assertThatExceptionOfType(NoSessionsChosenException.class)
                .isThrownBy(()->service.create(deelnemer1));
    }
}
