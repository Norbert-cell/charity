package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    public Institution findById(long institutionId) {
        return institutionRepository.findById(institutionId).orElse(new Institution());
    }

    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    public void remove(long institutionId) {
        Institution institution = institutionRepository.findById(institutionId).orElse(new Institution());
        institutionRepository.delete(institution);
    }
}
