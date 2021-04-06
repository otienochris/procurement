package com.otienochris.procurement_management_system.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Supplier {

    @Id
    private String kRA;
    private String name;
    private String description;

}
