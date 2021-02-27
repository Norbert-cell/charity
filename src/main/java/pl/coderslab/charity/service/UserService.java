package pl.coderslab.charity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }


    public boolean isExistEmail(String email) {
        Optional<User> byUserName = userRepository.findByUsername(email);
        return byUserName.isPresent();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findUserByEmail(String name) {
        return userRepository.findByUsername(name);
    }

    public Role checkAuthority(String name) {
        return userRepository.findByUsername(name).orElse(new User()).getRole();
    }

    public List<User> findAllWhereRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    public Optional<User> findById(long userId) {
        return userRepository.findById(userId);
    }

    public String getPrincipalName(String name) {
        return userRepository.findByUsername(name).orElse(new User()).getFullName();
    }

    public void remove(User user) {
        userRepository.delete(user);
    }

    public void blockUser(long id) {
        User user = userRepository.findById(id).orElse(new User());
        if (user.isAccountNonLocked()) {
            user.setAccountNonLocked(false);
        } else {
            user.setAccountNonLocked(true);
        }
        userRepository.save(user);
    }



    public void updatePassword(String updatedPassword, Long userId) {
        entityManager.createQuery("update User u set u.password=:updatedPassword where u.id=:userId")
                .setParameter("updatedPassword", updatedPassword)
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
