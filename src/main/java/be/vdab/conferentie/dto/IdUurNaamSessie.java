package be.vdab.conferentie.dto;

import java.time.LocalTime;

public record IdUurNaamSessie(long id, LocalTime uur, String naam) {
}
