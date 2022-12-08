package be.vdab.conferentie.repositories;

import be.vdab.conferentie.exceptions.NoSessionsChosenException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DeelnemerVoorkeurSessieRepository {
    private final JdbcTemplate template;

    public DeelnemerVoorkeurSessieRepository(JdbcTemplate template) {
        this.template = template;
    }

    public int create(long deelnemerId, Set<Long> sessieIds) {
        if (sessieIds.isEmpty()) throw new NoSessionsChosenException();
        var sql = """
                insert into deelnemervoorkeursessies(deelnemerId, sessieId) values
                """;
        sql += "(" + deelnemerId + ", ?),".repeat(sessieIds.size() - 1);
        sql += "(" + deelnemerId + ", ?)";
        return template.update(sql, sessieIds.toArray());
    }
}
