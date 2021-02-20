package com.otienochris.procurement_management_system.controllers;


import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/id/{userId}")
    public Optional<User> getUserById(@RequestParam("userId") Long userId){
        return userService.getUserById(userId);
    }

    @GetMapping("/email/{email}")
    public Optional<User> getUserByEmail(@RequestParam("email") String email){
        return userService.getUserByEmail(email);
    }

    @PostMapping("/add")
    public List<User> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/delete/{id}")
    public List<User> deleteUserById(@RequestParam("id") Long id){
        return userService.deleteUserById(id);
    }

    @PostMapping("/delete")
    public List<User> deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }

    @PostMapping("/update")
    public Optional<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

}
