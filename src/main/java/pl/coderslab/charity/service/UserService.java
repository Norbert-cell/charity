package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean isExistEmail(String email) {
        Optional<User> byUserName = userRepository.findByUsername(email);
        return byUserName.isPresent();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findUserByEmail(String name) {
        return userRepository.findByUsername(name).orElse(new User());
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
}
