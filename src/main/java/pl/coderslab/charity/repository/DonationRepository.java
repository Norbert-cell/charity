package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "select COUNT(quantity) from donation", nativeQuery=true)
    int countAllByQuantity();

    @Query(value = "select COUNT(*) from donation", nativeQuery=true)
    int countAll();

}
