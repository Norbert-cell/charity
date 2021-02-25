package pl.coderslab.charity.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Archives;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


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

    @Async
    @Scheduled(fixedRate = 100000)
    public void setStatus(){
        List<Donation> donations = donationService.checkIsDateAfterDateNowAndStatusIsPlaced();
        if (donations.isEmpty()){

        } else {
            for (Donation donation : donations) {
                Archives archives = new Archives();
                archives.setLocalDate(LocalDate.now());
                archives.setLocalTime(LocalTime.now());
                archives.setStatus(Status.RECEIVED);
                archivesService.save(archives);
                donation.getArchives().add(archives);
                entityManager.createQuery("update Donation d set d.status=?1 where d.id=?2")
                        .setParameter(1,Status.RECEIVED)
                        .setParameter(2,donation.getId())
                        .executeUpdate();

            }

        }
    }

}
