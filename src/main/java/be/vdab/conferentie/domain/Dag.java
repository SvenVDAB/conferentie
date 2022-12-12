package be.vdab.conferentie.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record Dag(long id, @DateTimeFormat(style = "S-") LocalDate datum) {
    public Dag(long id, LocalDate datum) {
        this.id = id;
        this.datum = datum;
    }

    @Override
    public LocalDate datum() {
        return datum;
    }
}
