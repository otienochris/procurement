package com.otienochris.procurement_management_system.repositories;

import com.otienochris.procurement_management_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

//    @Query("SELECT new User (u.username, u.roles,u.isActive, u.dataCreated, u.dateModified) FROM User as u ORDER BY u.dataCreated ASC")
    @Override
    List<User> findAll();

    @Query("SELECT new User (u.username, u.isActive, u.dateCreated, u.dateModified) FROM User u WHERE u.username=:userName")
    Optional<User> findByUsername(String userName);

    @Override
    boolean existsById(String userName);
}
