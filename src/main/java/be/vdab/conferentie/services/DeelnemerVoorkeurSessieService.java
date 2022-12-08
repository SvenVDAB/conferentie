package be.vdab.conferentie.services;

import be.vdab.conferentie.repositories.DeelnemerVoorkeurSessieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class DeelnemerVoorkeurSessieService {
    private final DeelnemerVoorkeurSessieRepository deelnemerVoorkeurSessieRepository;

    public DeelnemerVoorkeurSessieService(DeelnemerVoorkeurSessieRepository deelnemerVoorkeurSessieRepository) {
        this.deelnemerVoorkeurSessieRepository = deelnemerVoorkeurSessieRepository;
    }

    public int create(long deelnemerId, Set<Long> sessieIds) {
        return deelnemerVoorkeurSessieRepository.create(deelnemerId, sessieIds);
    }
}
