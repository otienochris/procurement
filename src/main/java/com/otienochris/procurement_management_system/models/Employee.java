package com.otienochris.procurement_management_system.models;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "employees")
@Transactional
public class Employee {

    @Id
    private String empId;
    private String name;
    private String email;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user;
}
