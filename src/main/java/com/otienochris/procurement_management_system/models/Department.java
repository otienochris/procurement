package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Department {
    @Id
    private int id;
    private String name;
    private String Description;
}
