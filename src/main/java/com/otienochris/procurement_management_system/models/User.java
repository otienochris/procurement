package com.otienochris.procurement_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String username;
    private String password;
    private Boolean isActive;

    @CreationTimestamp
    private Timestamp dataCreated;

    @UpdateTimestamp
    private Timestamp dateModified;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(String username, Set<Role> roles, Boolean isActive, Date dataCreated, Date dateModified) {
        this.username = username;
        this.isActive = isActive;
        this.roles = roles;
        this.dataCreated = (Timestamp) dataCreated;
        this.dateModified = (Timestamp) dateModified;
    }

    public User(String username,  Boolean isActive, Date dataCreated, Date dateModified) {
        this.username = username;
        this.isActive = isActive;
//        this.roles = roles;
        this.dataCreated = (Timestamp) dataCreated;
        this.dateModified = (Timestamp) dateModified;
    }
}
