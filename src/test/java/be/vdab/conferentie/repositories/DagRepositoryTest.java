package be.vdab.conferentie.repositories;

import be.vdab.conferentie.domain.Dag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(DagRepository.class)
public class DagRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String DAGEN = "dagen";
    private final DagRepository repository;

    public DagRepositoryTest(DagRepository repository) {
        this.repository = repository;
    }

    @Test
    void findAllGeeftAlleDagenGesorteerdOpDatum() {
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(DAGEN))
                .extracting(Dag::getDatum)
                .isSorted();
    }
}
