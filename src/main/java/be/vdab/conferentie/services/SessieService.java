package be.vdab.conferentie.services;

import be.vdab.conferentie.dto.IdUurNaamSessie;
import be.vdab.conferentie.dto.SessieDetails;
import be.vdab.conferentie.repositories.SessieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SessieService {
    private final SessieRepository sessieRepository;

    public SessieService(SessieRepository sessieRepository) {
        this.sessieRepository = sessieRepository;
    }

    public List<IdUurNaamSessie> findByDayId(long dagId) {
        return sessieRepository.findByDayId(dagId);
    }

    public Optional<SessieDetails> findBySessieId(long sessieId) {
        return sessieRepository.findBySessieId(sessieId);
    }
}
