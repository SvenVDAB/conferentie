package be.vdab.conferentie.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Dag {
    private final long id;
    @DateTimeFormat(style = "S-")
    private final LocalDate datum;

    public Dag(long id, LocalDate datum) {
        this.id = id;
        this.datum = datum;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDatum() {
        return datum;
    }
}
