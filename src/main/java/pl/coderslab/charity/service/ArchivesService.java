package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Archives;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Status;
import pl.coderslab.charity.repository.ArchivesRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Service
public class ArchivesService {

    private final ArchivesRepository archivesRepository;

    public ArchivesService(ArchivesRepository archivesRepository) {
        this.archivesRepository = archivesRepository;
    }


    public void save(Archives archives) {
        archivesRepository.save(archives);
    }

    public void delete(Archives archives){
        archivesRepository.delete(archives);
    }

    public void updateStatus(Archives archives) {
        archivesRepository.save(archives);
    }
}
