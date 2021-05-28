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
@Table(name = "requests_for_quotation")
public class RequestForQuotation {

    @Id
    @GeneratedValue
    private Integer id;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Timestamp dateModified;

    @Column(columnDefinition = "varchar(500)")
    private String message;

    @OneToOne(cascade = CascadeType.ALL)
    private Document quotationDocument;

    @OneToOne(cascade = CascadeType.ALL)
    private Document termsAndConditions;

    private Integer purchaseOrderId;

}
