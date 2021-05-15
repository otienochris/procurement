package com.otienochris.procurement_management_system.services;

import com.otienochris.procurement_management_system.models.*;
import com.otienochris.procurement_management_system.repositories.DepartmentHeadRepo;
import com.otienochris.procurement_management_system.repositories.EmployeeRepo;
import com.otienochris.procurement_management_system.repositories.SupplierRepo;
import com.otienochris.procurement_management_system.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailSendingService mailSendingService;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private DepartmentHeadRepo departmentHeadRepo;

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
        Optional<User> user = userRepository.findById(userName);
        UserDetailsImpl userDetails = null;
        if (user.isPresent())
            userDetails = new UserDetailsImpl(user.get());
        else
            throw new UsernameNotFoundException("User with username: " + userName + " cannot not be found!");

        return userDetails;
    }

    public void verifyEmail(String token) {
        userRepository.findByEmailVerificationToken(token).ifPresentOrElse(user -> {
            user.setIsActive(true);
            user.setEmailVerificationToken(null);
            userRepository.save(user);
        }, () -> {
            throw new NoSuchElementException("Invalid verification token!");
        });
    }

    public void sendEmailVerificationToken(String username, String email) {
        userRepository.findById(username).ifPresentOrElse(user -> {

            UUID token = UUID.randomUUID();
            user.setEmailVerificationToken(token.toString());
            userRepository.save(user);

            String siteUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/users/verifyEmail/" + token)
                    .toUriString();

            String body = "<h4>Dear " + username + ",</h4>" +
                    "<p>Please click the link below to activate your account: </p>" +
                    "<h3> <a href=\"" + siteUrl + "\"> " + "Verify Email" + "</a> </h3>" +
                    " <h4> Or use the code <h1>"+ token + "</h1> to activate your account </h4>"+
                    "<p>Thank you <br/> The procurement team</p>";

            try {
                mailSendingService.sendSimpleEmail(email, "christopherochiengotieno@gmail.com", "Email Verification", body);
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }, () -> {
            throw new UsernameNotFoundException("The user does not exist: Please, signup to continue");
        });
    }

    public void sendEmailVerificationToken(String username) {
        userRepository.findById(username).ifPresentOrElse(user -> {

            UUID token = UUID.randomUUID();
            user.setEmailVerificationToken(token.toString());
            userRepository.save(user);

            AtomicReference<String> email = new AtomicReference<>("");

            employeeRepo.findByUser_Username(username).ifPresent(employee -> {
                email.set(employee.getEmail());
            });
            supplierRepo.findByUser_Username(username).ifPresent(supplier -> {
                email.set(supplier.getEmail());
            });

            departmentHeadRepo.findByUser_Username(username).ifPresent(departmentHead -> {
                email.set(departmentHead.getEmail());
            });

//            String siteUrl = request.getContextPath() + "/api/v1/users/verifyEmail/" + token;
            String siteUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/users/verifyEmail/" + token)
                    .toUriString();

            String body = "<h4>Dear " + username + ",</h4>" +
                    "<p>Please click the link below to activate your account: </p>" +
                    "<h3> <a href=\"" + siteUrl + "\"> " + "Verify Email" + "</a> </h3>" +
                    " <h4> Or use the code <h1>"+ token + "</h1> to activate your account </h4>"+
                    "<p>Thank you <br/> The procurement team</p>";

            try {
                mailSendingService.sendSimpleEmail(email.get(), "christopherochiengotieno@gmail.com", "Email Verification", body);
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }, () -> {
            throw new UsernameNotFoundException("The user does not exist: Please, signup to continue");
        });
    }

    public void sendChangePasswordToken(String toEmail) {

        String username = "";
        User user = null;
        if (employeeRepo.existsByEmail(toEmail)){
            Employee employee = employeeRepo.findByEmail(toEmail).get();
            user = employee.getUser();
            username = employee.getName();
        } else if (supplierRepo.existsByEmail(toEmail)){
            Supplier supplier = supplierRepo.findByEmail(toEmail).get();
            user = supplier.getUser();
            username = supplier.getName();
        }
        else if (departmentHeadRepo.existsByEmail(toEmail)){
            DepartmentHead departmentHead = departmentHeadRepo.findByEmail(toEmail).get();
            user = departmentHead.getUser();
            username = departmentHead.getName();
        }
//        todo track this
        if (user != null) {
            UUID token = UUID.randomUUID();
            user.setChangePasswordToken(token.toString());
            userRepository.save(user);

            String siteUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/users/changePassword/" + token)
                    .toUriString();

            String body =
                    "<div style= ' background-color: white; color: black'> <h4 >Dear " + username + ",</h4> </div>"+
                    "<div style= ' margin: 0; background-color: white; color: black'> <h4> Use the code below to change your password:</div>" +
                    "<div style= ' display: flex; justify-content:center; align-items:center; margin: 0; background-color: orange; text-align: center ; color: black'> <h1>"+ token + "</h1> </div>" +
                    "<div> <p style= 'color: black'>Thank you <br/> The procurement team</p> </div>" ;
            try {
                mailSendingService.sendSimpleEmail(toEmail,
                        "christopherochiengotieno@gmail.com",
                        "Change Password",
                        body);
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            throw new NoSuchElementException("User not found!");
        }
    }

    public void changePassword(String token, String encodedPassword) {
        userRepository.findByChangePasswordToken(token).ifPresentOrElse(user -> {
            user.setPassword(encodedPassword);
            user.setChangePasswordToken(null);
            userRepository.save(user);
        }, () -> {
            throw new NoSuchElementException("Invalid token");
        });
    }

}
