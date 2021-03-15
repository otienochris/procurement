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

    @Version
//    @Null
    private Integer version;

    @Null
    @CreationTimestamp
    private Timestamp creationDate;

    @Null
    @UpdateTimestamp
    private Timestamp modificationDate;

    @Size(min = 5, max = 20)
    private String title;

    @Size(min = 10, max = 100)
    private String fileName;

    @Lob
    @NotNull
    private byte[] content;

}
