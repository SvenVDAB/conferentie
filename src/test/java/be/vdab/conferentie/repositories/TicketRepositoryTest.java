package be.vdab.conferentie.repositories;

import be.vdab.conferentie.exceptions.GeenTicketsBeschikbaarException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(TicketRepository.class)
@Sql("/changeNumberOfTickets.sql")
public class TicketRepositoryTest {
    private final TicketRepository repository;

    public TicketRepositoryTest(TicketRepository repository) {
        this.repository = repository;
    }

    @Test
    void AantalTicketsIsPositiefKanVerminderdWordenTotMinimumNulAndersGeenTicketsBeschikbaarException() {
        var aantalTickets = repository.findAantalBeschikbareTickets();
        assertThat(aantalTickets).isEqualTo(5);
        while (aantalTickets > 0) {
            repository.lowerNrOfTicketsByOne();
            aantalTickets--;
            assertThat(aantalTickets).isEqualTo(repository.findAantalBeschikbareTickets());
        }
        assertThatExceptionOfType(GeenTicketsBeschikbaarException.class).isThrownBy(
                () -> repository.lowerNrOfTicketsByOne()
        );
    }
}
