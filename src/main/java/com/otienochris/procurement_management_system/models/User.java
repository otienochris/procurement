package com.otienochris.procurement_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// lombok annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// persistence
@Entity
public class User extends Person implements ClientInterface{

    @Id
    @GeneratedValue
    private Long userId;
    private String role;
    private String email;


    @Override
    public String getClientId() {
        return userId.toString();
    }
}
