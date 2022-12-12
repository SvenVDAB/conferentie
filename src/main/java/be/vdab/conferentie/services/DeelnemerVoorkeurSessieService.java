package be.vdab.conferentie.services;

import be.vdab.conferentie.repositories.DeelnemerVoorkeurSessieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeelnemerVoorkeurSessieService {
    private final DeelnemerVoorkeurSessieRepository deelnemerVoorkeurSessieRepository;

    public DeelnemerVoorkeurSessieService(DeelnemerVoorkeurSessieRepository deelnemerVoorkeurSessieRepository) {
        this.deelnemerVoorkeurSessieRepository = deelnemerVoorkeurSessieRepository;
    }
}
