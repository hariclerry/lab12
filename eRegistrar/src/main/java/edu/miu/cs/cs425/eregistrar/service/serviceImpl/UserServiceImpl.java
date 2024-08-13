package edu.miu.cs.cs425.eregistrar.service.serviceImpl;

import edu.miu.cs.cs425.eregistrar.model.User;
import edu.miu.cs.cs425.eregistrar.repository.UserRepository;
import edu.miu.cs.cs425.eregistrar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    public User saveUser(User user) {
        // Encrypt the password using BCrypt
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        if (user != null && encodedPassword != null && encodedPassword.equals(user.getPassword())) {
            return user; // or return a token or DTO instead
        }
        return null;
    }
    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
