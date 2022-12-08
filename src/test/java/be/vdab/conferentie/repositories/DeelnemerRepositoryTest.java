package be.vdab.conferentie.repositories;

import be.vdab.conferentie.domain.Deelnemer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(DeelnemerRepository.class)
@Sql("/insertTestDeelnemer.sql")
public class DeelnemerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String DEELNEMERS = "deelnemers";
    private final DeelnemerRepository repository;

    public DeelnemerRepositoryTest(DeelnemerRepository repository) {
        this.repository = repository;
    }

    @Test
    void create() {
        var id = repository.create(new Deelnemer(0, "testSven", "testBouwen",
                "svenver28@hotmail.com"));
        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(DEELNEMERS, "id = " + id)).isOne();
    }

    @Test
    void existsBestaandeEmailGeeftTrue() {
        var bestaat = repository.exists("testMail@test.be");
        assertThat(bestaat).isTrue();
    }

    @Test
    void existsOnbestaandeEmailGeeftFalse() {
        var bestaat = repository.exists("nonexistingTestMail@test.be");
        assertThat(bestaat).isFalse();
    }
}
