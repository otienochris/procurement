package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, updatable = false, columnDefinition = "varchar", nullable = false)
    private UUID departmentId;

//    @Column(unique = true)
    private String name;

    private String Description;
}
