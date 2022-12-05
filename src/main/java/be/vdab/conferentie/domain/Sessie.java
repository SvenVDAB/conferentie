package be.vdab.conferentie.domain;

import java.time.LocalTime;

public class Sessie {
    private final long id;
    private final String naam;
    private final long dagId;
    private final LocalTime uur;
    private final long sprekerId;
    private final int interesses;

    public Sessie(long id, String naam, long dagId, LocalTime uur, long sprekerId, int interesses) {
        this.id = id;
        this.naam = naam;
        this.dagId = dagId;
        this.uur = uur;
        this.sprekerId = sprekerId;
        this.interesses = interesses;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getDagId() {
        return dagId;
    }

    public LocalTime getUur() {
        return uur;
    }

    public long getSprekerId() {
        return sprekerId;
    }

    public int getInteresses() {
        return interesses;
    }
}

