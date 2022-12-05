package be.vdab.conferentie.domain;

public class Deelnemer {
    private final long id;
    private final String voornaam;
    private final String familienaam;
    private final String email;

    public Deelnemer(long id, String voornaam, String familienaam, String email) {
        this.id = id;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getEmail() {
        return email;
    }
}
