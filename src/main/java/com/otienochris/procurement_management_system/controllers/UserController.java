package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName){
        return new ResponseEntity<>(userService.getUserByUserName(userName), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword())); // encode the password
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName){
        userService.deleteUser(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable("userName") String userName, @RequestBody User newUser){
        userService.updateUser(userName, newUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
// todo use dtos to pass data from the users