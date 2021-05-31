package com.otienochris.procurement_management_system.controllers;

import com.otienochris.procurement_management_system.Dtos.AuthenticationRequestDto;
import com.otienochris.procurement_management_system.Dtos.UsernameObject;
import com.otienochris.procurement_management_system.models.ChangePasswordObject;
import com.otienochris.procurement_management_system.models.NewPasswordObject;
import com.otienochris.procurement_management_system.models.User;
import com.otienochris.procurement_management_system.responses.AuthenticationResponse;
import com.otienochris.procurement_management_system.services.UserService;
import com.otienochris.procurement_management_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager; // for authenticating the passed username and password
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(userService.getUserByUserName(userName), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // encode the password
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable("userName") String userName) {
        userService.deleteUser(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable("userName") String userName, @RequestBody User newUser) {
        userService.updateUser(userName, newUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthenticationRequestDto user) {
        System.out.println(user);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Incorrect username and password");
        }

        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(jwtToken).build());
    }

    @GetMapping("/verifyEmail/{emailVerificationToken}")
    public ResponseEntity<?> verifyEmail(@PathVariable("emailVerificationToken") String token) {
        userService.verifyEmail(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/verifyEmail/sendCode/")
    public ResponseEntity<?> sendEmailVerificationCode(@RequestBody UsernameObject username) {

        userService.sendEmailVerificationToken(username.getUsername());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> sendChangePassword(@RequestBody ChangePasswordObject emailObject) {
        userService.sendChangePasswordToken(emailObject.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/submitNewPassword")
    public ResponseEntity<?> submitNewPassword(@RequestBody NewPasswordObject newPasswordObject) {
        userService.changePassword(newPasswordObject.getToken(), passwordEncoder.encode(newPasswordObject.getNewPassword()));
     return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
// todo use dtos to pass data from the users