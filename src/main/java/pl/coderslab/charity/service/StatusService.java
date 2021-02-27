package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
@Transactional
public class StatusService {

    private final DonationService donationService;
    private final ArchivesService archivesService;

    @PersistenceContext
    private EntityManager entityManager;

    public StatusService(DonationService donationService, ArchivesService archivesService) {
        this.donationService = donationService;
        this.archivesService = archivesService;
    }


}
