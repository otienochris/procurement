package com.otienochris.procurement_management_system.models;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class DepartmentHead {
    @Id
    private Integer id;
    private String name;
    private String email;
    private String password;
    @OneToOne(targetEntity = Department.class)
    public Department department;

}
