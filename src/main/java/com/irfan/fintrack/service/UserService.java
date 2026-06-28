package com.irfan.fintrack.service;

import com.irfan.fintrack.model.User;
import com.irfan.fintrack.repository.UserRepository;
import com.irfan.fintrack.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with that email."));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user with the given id."));
    }

    public User updateUserById(Long id, User user) {
        User existing = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No user with the given id."));

        if (user.getName() != null)
            existing.setName(user.getName());

        if (user.getEmail() != null) {
            if(userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new RuntimeException("Email already exists.");
            }
            existing.setEmail(user.getEmail());
        }

        if (user.getPassword() != null)
            existing.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getCollege() != null)
            existing.setCollege(user.getCollege());

        return userRepository.save(existing);
    }

    public User createUser(UserCreateRequest req) {

        if(userRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .college(req.getCollege())
                .joinedDate(LocalDate.now())
                .build();

        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found.");
        }
        userRepository.deleteById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
