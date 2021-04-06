package com.otienochris.procurement_management_system.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class RequestForQuotation {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @NotNull
    @Column(columnDefinition = "varchar(500)")
    private String message;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Document quotationDocument;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Document termsAndConditions;

    @NotNull
    private UUID purchaseOrderId;

}
