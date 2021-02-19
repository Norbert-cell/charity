package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;

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
}
