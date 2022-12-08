package be.vdab.conferentie.repositories;

import be.vdab.conferentie.domain.Dag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DagRepository {
    private final JdbcTemplate template;

    public DagRepository(JdbcTemplate template) {
        this.template = template;
    }

    private final RowMapper<Dag> dagMapper =
            (result, rowNum) ->
                    new Dag(result.getLong("id"), result.getObject("datum", LocalDate.class));

    public List<Dag> findAll() {
        var sql = """
                select id, datum
                from dagen
                order by datum
                """;
        return template.query(sql, dagMapper);
    }
}
