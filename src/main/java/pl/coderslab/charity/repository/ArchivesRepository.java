package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Archives;

@Repository
public interface ArchivesRepository extends JpaRepository<Archives, Long> {
}
