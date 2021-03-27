package com.otienochris.procurement_management_system.models;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Supplier {
    @Id
    private int id;
    private Long KRA;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getKRA() {
        return KRA;
    }

    public void setKRA(Long KRA) {
        this.KRA = KRA;
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

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", KRA=" + KRA +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
