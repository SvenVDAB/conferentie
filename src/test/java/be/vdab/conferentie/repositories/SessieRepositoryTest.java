package be.vdab.conferentie.repositories;

import be.vdab.conferentie.dto.IdUurNaamSessie;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import({SessieRepository.class, DagRepository.class})
@Sql("/insertTestSessies.sql")
public class SessieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final SessieRepository sessieRepository;

    public SessieRepositoryTest(SessieRepository repository) {
        this.sessieRepository = repository;
    }

    @Test
    void findByDayIdGeeftLegeLijstVoorOnbestaandeDag() {
        assertThat(sessieRepository.findByDayId(-1L)).isEmpty();
    }

    private long idVanTestDay1() { // dag waarin geen sessies plaatsvinden
        return jdbcTemplate.queryForObject("select id from dagen where datum = '1000-01-01'", Long.class);
    }

    @Test
    void findByDayIdGeeftLegeLijstVoorDagWaarinGeenSessiesPlaatsvinden() {
        assertThat(sessieRepository.findByDayId(idVanTestDay1())).isEmpty();
    }

    private long idVanTestDay2() { // dag waarin twee sessies plaatsvinden
        return jdbcTemplate.queryForObject("select id from dagen where datum = '2999-12-31'", Long.class);
    }

    @Test
    void findByDayId() {
        assertThat(sessieRepository.findByDayId(idVanTestDay2()))
                .hasSize(2)
                .extracting(IdUurNaamSessie::naam)
                .contains("testSessie", "testSessie2");
    }

    private long idVanTestSessie2() {
        return jdbcTemplate.queryForObject("select id from sessies where naam = 'testSessie2'", Long.class);
    }

    @Test
    void findBySessieId() {
        assertThat(sessieRepository.findBySessieId(idVanTestSessie2()))
                .hasValueSatisfying(sessieDetails -> assertThat(sessieDetails.sessieNaam()).isEqualTo("testSessie2"));
    }

    @Test
    void findByOnbestaandeSessieIdVindtGeenSessie () {
        assertThat(sessieRepository.findBySessieId(-1L)).isEmpty();
    }
}
