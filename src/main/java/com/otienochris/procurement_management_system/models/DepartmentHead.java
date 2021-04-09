package com.otienochris.procurement_management_system.models;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Transactional
@Table(name = "department_heads")
public class DepartmentHead {
    @Id
    private String empId;
    private String name;
    private String email;
    private String password;

    @OneToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_id")
    public Department department;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "username")
    public User user;


}
