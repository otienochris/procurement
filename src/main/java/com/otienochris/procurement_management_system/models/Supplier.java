package com.otienochris.procurement_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Supplier {
    @Id
    private String KRA;
    private String name;
    private String description;
    private String password;


}
