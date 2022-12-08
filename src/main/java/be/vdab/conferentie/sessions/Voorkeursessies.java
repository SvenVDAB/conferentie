package be.vdab.conferentie.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@SessionScope
public class Voorkeursessies implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Set<Long> sessieIds = new LinkedHashSet<>();

    public void voegToe(long id) {
        sessieIds.add(id);
    }

    public void verwijder(long id) {
        sessieIds.remove(id);
    }

    public void clear() {
        sessieIds.clear();
    }

    public Set<Long> getSessieIds() {
        return sessieIds;
    }
}
