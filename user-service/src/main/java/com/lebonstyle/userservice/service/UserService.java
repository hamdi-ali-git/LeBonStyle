package com.lebonstyle.userservice.service;

import com.lebonstyle.userservice.entity.User;
import com.lebonstyle.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User User) {
        userRepository.save(User);
    }

    public boolean deleteUserByUuid(UUID uuid) {
        Optional<User> User = userRepository.findByUuid(uuid);
        if(User.isPresent()) {
            userRepository.delete(User.get());
            return true;
        }
        return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUuid(UUID uuid) {
        Optional<User> User = userRepository.findByUuid(uuid);
        return User.orElse(null);
    }

    public Optional<User> updateUserByUuid(UUID id, User updatedUser) {
        return userRepository.findByUuid(id).map(existingUser -> {
            if (updatedUser.getFirstName() != null) {
                existingUser.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null) {
                existingUser.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPhoneNumber() != null) {
                existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            }
            return userRepository.save(existingUser);
        });
    }

}
