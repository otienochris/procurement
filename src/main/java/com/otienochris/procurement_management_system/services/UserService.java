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

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> userRepository.delete(value));
    }

    public void addUser(User user){
        List<User> users = userRepository.findAll();
        /*for (User presentUser: users
             ) {
            if (user.getEmail().equalIgnoreCase(presentUser.getEmail())){
                return;
            }
        }*/
        userRepository.save(user);

    }
}
