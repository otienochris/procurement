package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.models.UserDetailsImpl;
import com.otienochris.procurement_management_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> {
            throw new NoSuchElementException("The user with username: " + userName + " does not exist!");
        });
    }

    public User saveUser(User user) {
        if (userRepository.existsById(user.getUsername()))
            throw new DuplicateKeyException("The username is already taken!");
        return userRepository.save(user);
    }

    public void deleteUser(String userName) {
        userRepository.findById(userName).ifPresentOrElse(userRepository::delete,
                () -> {
                    throw new NoSuchElementException("The user with username: " + userName + " does not exist!");
                });
    }

    public void updateUser(String userName, User newUser) {
        userRepository.findById(userName).ifPresentOrElse(user -> {
            user.setIsActive(newUser.getIsActive());
            user.setRoles(newUser.getRoles());
            userRepository.save(user);
        }, () -> {
            throw new NoSuchElementException("The user with username: " + userName + " does not exist!");
        });
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> byId = userRepository.findById(userName);
        UserDetailsImpl userDetails = null;
        if (byId.isPresent()){
            userDetails = new UserDetailsImpl(byId.get());
        } else {
            throw new UsernameNotFoundException("User with username: " + userName + " cannot not be found!");
        }
        return userDetails;
    }
}
