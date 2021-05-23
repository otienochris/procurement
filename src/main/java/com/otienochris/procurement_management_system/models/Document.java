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


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "documents")
public class Document {

    @Id
//    @Size(min = 1, max = 100, message = "The filename size must be between 1 to 100 letters")
    @Column(name = "file_name")
    private String fileName;

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

    @Lob
    @NotNull
    private byte[] content;

//    public Document(
//            @Null Date creationDate,
//            @Null Date modificationDate,
//            @Size(min = 5, max = 20) String type,
//            byte[] content,
//            @Size(min = 1, max = 100) String fileName
//            ) {
//        this.dateCreated = (Timestamp) creationDate;
//        this.dateModified = (Timestamp) modificationDate;
//        this.type = type;
//        this.fileName = fileName;
//        this.content = content;
//    }

    @Override
    public String toString() {
        return "Document{" +
                "fileName='" + fileName + '\'' +
                ", version=" + version +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", type='" + type + '\'' +
                '}';
    }
}
