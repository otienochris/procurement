package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "documents")
public class Document {

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @Size(min = 5, max = 20)
    private String type;

    @Size(min = 1, max = 100, message = "The filename size must be between 1 to 100 letters")
    @Column(name = "file_name")
    @Id
    private String fileName;

    @Lob
    @NotNull
    private byte[] content;

    public Document(
            @Null Date creationDate,
            @Null Date modificationDate,
            @Size(min = 5, max = 20) String type,
            @Size(min = 1, max = 100) String fileName) {
        this.dateCreated = (Timestamp) creationDate;
        this.dateModified = (Timestamp) modificationDate;
        this.type = type;
        this.fileName = fileName;
    }
}
