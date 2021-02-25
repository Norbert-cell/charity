package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Archives;
import pl.coderslab.charity.repository.ArchivesRepository;

@Service
public class ArchivesService {

    private final ArchivesRepository archivesRepository;

    public ArchivesService(ArchivesRepository archivesRepository) {
        this.archivesRepository = archivesRepository;
    }


    public void save(Archives archives) {
        archivesRepository.save(archives);
    }
}
