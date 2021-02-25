package pl.coderslab.charity.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.repository.TokenRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
@Transactional
public class TokenService {


    @PersistenceContext
    private EntityManager entityManager;

    @Async
    @Scheduled(cron = "0 0/30 * * * ?", zone = "Europe/Warsaw")
    public void deleteTokenWhenExpired(){
        Calendar instance = Calendar.getInstance();
        entityManager.createQuery("delete from Token t where t.expiryDate <=?1")
                .setParameter(1, instance.getTime())
                .executeUpdate();
        System.out.println(instance.getTime());
    }

}
