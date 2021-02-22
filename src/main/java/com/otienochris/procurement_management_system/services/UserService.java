package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public List<User> addUser(User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            return userRepository.findAll();
        }
        userRepository.save(user);
        return userRepository.findAll();
    }

    public List<User> deleteUserById(Long id){
        userRepository.findById(id).ifPresent(value -> userRepository.delete(value));
        return userRepository.findAll();
    }

    public Optional<User> updateUser(User user){
        userRepository.findById(user.getUserId()).ifPresent(value -> {
            value.setRole(user.getRole());
            value.setFirstName(user.getFirstName());
            value.setLastName(user.getLastName());
            value.setPassword(user.getPassword());
            if(userRepository.findByEmail(user.getEmail()).isEmpty())
                value.setEmail(user.getEmail());;
        });
        return userRepository.findById(user.getUserId());// if it returns null, the user does not exist
    }

    public List<User> deleteUser(User user) {
        userRepository.findById(user.getUserId()).ifPresent(value -> userRepository.delete(user));
        return userRepository.findAll();
    }
}
