package be.vdab.conferentie.repositories;

import be.vdab.conferentie.dto.IdUurNaamSessie;
import be.vdab.conferentie.dto.SessieDetails;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class SessieRepository {
    private final JdbcTemplate template;

    public SessieRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<IdUurNaamSessie> idUurNaamMapper =
            (result, rowNum) ->
                    new IdUurNaamSessie(result.getLong("id"), result.getObject("uur", LocalTime.class), result.getString("naam"));

    public List<IdUurNaamSessie> findByDayId(long dagId) {
        var sql = """
                select id, uur, naam
                from sessies
                where dagId = ?
                order by uur
                """;
        return template.query(sql, idUurNaamMapper, dagId);
    }

    private final RowMapper<SessieDetails> sessieDetailsMapper =
            (result, rowNum) ->
                    new SessieDetails(result.getString("naam"),
                            result.getObject("uur", java.time.LocalTime.class),
                            result.getString("voornaam"), result.getString("familienaam"),
                            result.getString("titel"), result.getString("firma"));

    public Optional<SessieDetails> findBySessieId(long sessieId) {
        try {
            var sql = """
                    select naam, uur, voornaam, familienaam, titel, firma
                    from sessies inner join sprekers
                    on sessies.sprekerid = sprekers.id
                    where sessies.id = ?
                    """;
            return Optional.of(template.queryForObject(sql, sessieDetailsMapper, sessieId));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
}

