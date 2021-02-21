package pl.coderslab.charity.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

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

    public User findById(long userId) {
        return userRepository.findById(userId).orElse(new User());
    }

    public String getPrincipalName(String name) {
        return userRepository.findByUsername(name).orElse(new User()).getFullName();
    }

    public void remove(long userId) {
        User user = userRepository.findById(userId).orElse(new User());
        userRepository.delete(user);
    }

    public void blockUser(long id) {
        User user = userRepository.findById(id).orElse(new User());
        if (user.isAccountNonLocked()){
            user.setAccountNonLocked(false);
        }else {
            user.setAccountNonLocked(true);
        }
        userRepository.save(user);
    }
// reset password
    public void createPasswordResetTokenForUser(User user, String token) {
        Token myToken = new Token(token, user);
        tokenRepository.save(myToken);
    }

    public void updatePassword(String updatedPassword, long id) {
        User user = userRepository.findById(id).orElse(new User());
        user.setPassword(updatedPassword);
        userRepository.save(user);
    }
}
