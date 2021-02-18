package com.otienochris.procurement_management_system.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// persistence
@Entity
public class Company implements ClientInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyId;
    private String kraPin;
    private String name;
    private String description;

    @Override
    public String getClientId() {
        return kraPin;
    }

    public Long getCompanyId() {
        return companyId;
    }


    public String getKraPin() {
        return kraPin;
    }

    public void setKraPin(String kraPin) {
        this.kraPin = kraPin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
