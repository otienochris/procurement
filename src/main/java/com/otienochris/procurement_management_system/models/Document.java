package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Null
    private  Long id;

//    @Null
    @Version
    private Integer version;

    @Null
    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @Null
    @UpdateTimestamp
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @Size(min = 5, max = 20)
    private String title;

    @Size(min = 1, max = 100)
    @Column(name = "file_name")
    private String fileName;

    @Lob
    @NotNull
    private byte[] content;

    public Document(Long id,
                    @Null Date creationDate,
                    @Null  Date modificationDate,
                    @Size(min = 5, max = 20) String title,
                    @Size(min = 1, max = 100) String fileName) {
        this.id = id;
        this.version = version;
        this.dateCreated = (Timestamp) creationDate;
        this.dateModified = (Timestamp) modificationDate;
        this.title = title;
        this.fileName = fileName;
    }


    public String toString() {
        Long var10000 = this.getId();
        return "Document(id=" + var10000 + ", version=" + this.getVersion() + ", dateCreated=" + this.getDateCreated() + ", dateModified=" + this.getDateModified() + ", title=" + this.getTitle() + ", fileName=" + this.getFileName()+ ")";
    }
}
