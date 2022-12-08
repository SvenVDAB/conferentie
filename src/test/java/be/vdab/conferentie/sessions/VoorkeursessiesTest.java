package be.vdab.conferentie.sessions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VoorkeursessiesTest {
    private Voorkeursessies voorkeursessies;

    @BeforeEach
    void beforeEach() {
        voorkeursessies = new Voorkeursessies();
    }

    @Test
    void eenNieuwMandjeIsLeeg() {
        assertThat(voorkeursessies.getSessieIds()).isEmpty();
    }

    @Test
    void nadatJeEenItemInHetMandjeLegtBevatDitMandjeEnkelDitItem() {
        voorkeursessies.voegToe(10L);
        assertThat(voorkeursessies.getSessieIds()).containsOnly(10L);
    }

    @Test
    void jeKanEenItemMaar1KeerToevoegenAanHetMandje() {
        voorkeursessies.voegToe(10L);
        voorkeursessies.voegToe(10L);
        assertThat(voorkeursessies.getSessieIds()).containsOnly(10L);
    }

    @Test
    void nadatJeTweeItemsInHetMandjeLegtBevatDitMandjeEnkelDieItems() {
        voorkeursessies.voegToe(10L);
        voorkeursessies.voegToe(20L);
        assertThat(voorkeursessies.getSessieIds()).containsOnly(10L, 20L);
    }
}
