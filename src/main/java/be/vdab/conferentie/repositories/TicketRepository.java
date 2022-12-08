package be.vdab.conferentie.repositories;

import be.vdab.conferentie.exceptions.GeenTicketsBeschikbaarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository {
    private final JdbcTemplate template;

    public TicketRepository(JdbcTemplate template) {
        this.template = template;
    }

    public int findAantalBeschikbareTickets() {
            var sql = """
                    select beschikbaar 
                    from tickets
                    """;
            return template.queryForObject(sql, Integer.class);
    }

    public void lowerNrOfTicketsByOne() {
        var sql = """
                update tickets
                set beschikbaar = beschikbaar - 1
                where beschikbaar > 0;
                """;
        if (template.update(sql) == 0) {
            throw new GeenTicketsBeschikbaarException();
        }
    }
}
