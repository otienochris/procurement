package com.otienochris.procurement_management_system.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.time.LocalDate;


// persistence
@Entity
public class Tender {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tenderId;
    private String description;
    private LocalDate date;
//    TODO implement the tender's attachment field
//    private File attachment;

    public Long getTenderId() {
        return tenderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    /*public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }*/
}
