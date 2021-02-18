package com.otienochris.procurement_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// lombok annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class Person {

    private String firstName;
    private String lastName;
    private String password;

}
