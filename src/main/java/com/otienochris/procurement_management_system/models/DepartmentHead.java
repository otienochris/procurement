package com.otienochris.procurement_management_system.models;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Transactional
public class DepartmentHead {
    @Id
    private String empId;
    private String name;
    private String email;
    private String password;
    @OneToOne(targetEntity = Department.class)
    public Department department;


}
