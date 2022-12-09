package be.vdab.conferentie.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class Deelnemer {
    private final long id;
    @NotBlank
    @Length(min=1, max=30)
    private final String voornaam;
    @NotBlank
    @Length(min=1, max=30)
    private final String familienaam;
    @Email
    @Length(min=1, max=80)
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
