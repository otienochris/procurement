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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String password;
    private Boolean isActive;

    @CreationTimestamp
    private Timestamp dateCreated;

    @UpdateTimestamp
    private Timestamp dateModified;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role roles;

    public User(String username, Role roles, Boolean isActive, Date dateCreated, Date dateModified) {
        this.username = username;
        this.isActive = isActive;
        this.roles = roles;
        this.dateCreated = (Timestamp) dateCreated;
        this.dateModified = (Timestamp) dateModified;
    }

    public User(String username,  Boolean isActive, Date dateCreated, Date dateModified) {
        this.username = username;
        this.isActive = isActive;
        this.dateCreated = (Timestamp) dateCreated;
        this.dateModified = (Timestamp) dateModified;
    }
}
