package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.Status;
import pl.coderslab.charity.entity.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "select sum(quantity) from donation", nativeQuery=true)
    Object countAllByQuantity();

    @Query(value = "select COUNT(*) from donation", nativeQuery=true)
    int countAll();

    @Query(value ="select count(*) from donation where institution_id =?1", nativeQuery=true)
    int countAllByInstitution(long institutionId);

    List<Donation> findAllByUser(User user);

    @Query(value="select * from donation where pick_up_date <= DATE(now()) and pick_up_time < time(now()) and status='PLACED'", nativeQuery=true)
    List<Donation> findAllByPickUpDateIsAfterAndPickUpTimeIsAfterAndStatusIs();

    @Query(value = "update donation set status=?2 where id=?1", nativeQuery=true)
    void updateStatus(long id,Status status);

    List<Donation> findAllByUserAndInstitution(User user, Institution institution);
}
