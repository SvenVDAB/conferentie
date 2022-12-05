package be.vdab.conferentie.domain;

public class Ticket {
    private final int beschikbaar;

    public Ticket(int beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    public int getBeschikbaar() {
        return beschikbaar;
    }
}
