package be.vdab.conferentie.services;

import be.vdab.conferentie.domain.Dag;
import be.vdab.conferentie.repositories.DagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DagService {
    private final DagRepository dagRepository;

    public DagService(DagRepository dagRepository) {
        this.dagRepository = dagRepository;
    }

    public List<Dag> findAll() {
        return dagRepository.findAll();
    }
}
