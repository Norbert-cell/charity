package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAllByVisibleIsTrue();
}
