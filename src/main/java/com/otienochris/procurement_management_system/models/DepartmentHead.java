package com.otienochris.procurement_management_system.models;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

@Entity
@Transactional
@Table(name = "department_heads")
public class DepartmentHead {
    @Id
    private String empId;
    private String name;
    private String email;
    private String departmentId;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    public User user;


}
