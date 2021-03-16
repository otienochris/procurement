package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.File;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class RequestForQuotation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Null
    private Long id;

    @Version
//    @Null
    private Integer version;

    @CreationTimestamp
    @Null
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
    @Null
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @NotNull
    @Column(columnDefinition = "varchar(500) not null")
    private String message;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Document quotationDocument;

    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Document termsAndConditions;

    @NotNull
    private Long purchaseOrderId;

}
