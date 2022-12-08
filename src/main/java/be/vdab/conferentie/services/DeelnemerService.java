package be.vdab.conferentie.services;

import be.vdab.conferentie.domain.Deelnemer;
import be.vdab.conferentie.repositories.DeelnemerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DeelnemerService {
    private final DeelnemerRepository deelnemerRepository;

    public DeelnemerService(DeelnemerRepository deelnemerRepository) {
        this.deelnemerRepository = deelnemerRepository;
    }

    @Transactional
    public long create(Deelnemer deelnemer) {
        return deelnemerRepository.create(deelnemer);
    }

    public boolean exists(String email) {
        return deelnemerRepository.exists(email);
    }
}
