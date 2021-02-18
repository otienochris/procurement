package com.otienochris.procurement_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


// lombok annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Client {

    @OneToMany
    private Tender tender;
}
