package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);

}
