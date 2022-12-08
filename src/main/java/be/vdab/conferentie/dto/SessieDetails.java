package be.vdab.conferentie.dto;

import java.time.LocalTime;

public record SessieDetails(String sessieNaam, LocalTime uur, String voornaam, String familienaam,
                            String titel, String firma) {
}
