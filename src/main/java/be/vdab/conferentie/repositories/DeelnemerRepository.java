package be.vdab.conferentie.repositories;

import be.vdab.conferentie.domain.Deelnemer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class DeelnemerRepository {
    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public DeelnemerRepository(JdbcTemplate template) {
        this.template = template;
        insert = new SimpleJdbcInsert(template)
                .withTableName("deelnemers")
                .usingGeneratedKeyColumns("id");
    }

    public long create(Deelnemer deelnemer) {
        return insert.executeAndReturnKey(
                        Map.of("voornaam", deelnemer.getVoornaam(),
                                "familienaam", deelnemer.getFamilienaam(),
                                "email", deelnemer.getEmail()))
                .longValue();
    }

    public boolean exists(String email) {
        var sql = """
                select count(*) from deelnemers
                where email = ?
                """;
        return template.queryForObject(sql, Long.class, email) > 0;
    }
}
