package be.vdab.conferentie.repositories;

import be.vdab.conferentie.exceptions.NoSessionsChosenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@JdbcTest
@Import(DeelnemerVoorkeurSessieRepository.class)
@Sql("/insertTestSessies.sql")
@Sql("/insertTestDeelnemer.sql")
public class DeelnemerVoorkeurSessieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final DeelnemerVoorkeurSessieRepository repository;

    public DeelnemerVoorkeurSessieRepositoryTest(DeelnemerVoorkeurSessieRepository repository) {
        this.repository = repository;
    }

    private long idVanTestDeelnemer() {
        return jdbcTemplate.queryForObject("select id from deelnemers where email = 'testMail@test.be'", Long.class);
    }

    private long idVanTestSessie() {
        return jdbcTemplate.queryForObject("select id from sessies where naam = 'testSessie'", Long.class);
    }

    private long idVanTestSessie2() {
        return jdbcTemplate.queryForObject("select id from sessies where naam = 'testSessie2'", Long.class);
    }

    @Test
    void create() {
        // opmerking: deze test is mee afhankelijk van
        // de goede werking van de classes DeelnemerRepository en SessieRepository
        assertThat(repository.create(idVanTestDeelnemer(), Set.of(idVanTestSessie(), idVanTestSessie2())))
                .isEqualTo(2);
    }

    @Test
    void createMetLegeSetWerptNoSessionsChosenException() {
        assertThatExceptionOfType(NoSessionsChosenException.class)
                .isThrownBy(() -> repository.create(idVanTestDeelnemer(), Set.of()));
    }
}
